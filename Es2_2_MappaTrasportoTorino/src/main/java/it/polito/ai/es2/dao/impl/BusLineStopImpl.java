package it.polito.ai.es2.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import it.polito.ai.es2.dao.BusLineStopInterface;
import it.polito.ai.es2.orm.conf.HibernateUtil;
import it.polito.ai.es2.orm.mapping.BusStop;

public class BusLineStopImpl implements BusLineStopInterface {

	public List<BusStop> getBusLineStops(String line) {
		
		List<BusStop> busStops = new ArrayList<BusStop>();
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hql = "select bs "
					+ "from BusLineStop bls, BusStop bs "
					+ "where bls.stopId = bs.id and bls.lineId = :line "
					+ "order by bls.seqenceNumber asc";
			TypedQuery<BusStop> query = session.createQuery(hql);
			query.setParameter("line", line);
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
