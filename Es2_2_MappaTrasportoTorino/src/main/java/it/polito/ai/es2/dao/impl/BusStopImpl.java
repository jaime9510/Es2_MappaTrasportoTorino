package it.polito.ai.es2.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import it.polito.ai.es2.dao.BusStopInterface;
import it.polito.ai.es2.orm.conf.HibernateUtil;
import it.polito.ai.es2.orm.mapping.BusStop;

public class BusStopImpl implements BusStopInterface {

	public List<BusStop> getAll() {
		
		List<BusStop> busStops = new ArrayList<BusStop>();
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hql = "from BusStop";
			TypedQuery<BusStop> query = session.createQuery(hql);
			busStops = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
//		HibernateUtil.shutdown();
		
		return busStops;
	
	}

}
