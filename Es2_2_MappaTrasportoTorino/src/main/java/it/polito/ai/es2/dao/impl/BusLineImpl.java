package it.polito.ai.es2.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import it.polito.ai.es2.dao.BusLineInterface;
import it.polito.ai.es2.orm.conf.HibernateUtil;
import it.polito.ai.es2.orm.mapping.BusLine;

public class BusLineImpl implements BusLineInterface {

	public List<BusLine> getAll() {

		List<BusLine> busLines = new ArrayList<BusLine>();
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hql = "from BusLine";
			TypedQuery<BusLine> query = session.createQuery(hql);
			busLines = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
//		HibernateUtil.shutdown();
		
		return busLines;
	}

}
