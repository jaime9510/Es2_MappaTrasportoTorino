package it.polito.ai.es2.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.polito.ai.es2.orm.conf.HibernateUtil;
import it.polito.ai.es2.orm.mapping.BusLine;
import it.polito.ai.es2.orm.mapping.BusStop;

public class MainApp {

	public static void main(String[] args) {
		
		List<BusLine> busLines = new ArrayList<BusLine>();
		List<BusStop> busStops = new ArrayList<BusStop>();
		List<String> select = new ArrayList<String>();
		List<Short> seqence = new ArrayList<Short>();
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();

			String hql = "from BusLine";
			TypedQuery<BusLine> query = session.createQuery(hql);
			busLines = query.getResultList();
			
			for(BusLine bl : busLines) {
				System.out.println(bl.getLine());
			}
			
//			String hql = "from BusStop";
//			Query query = session.createQuery(hql);
//			busStops= (List<BusStop>)query.list();
//			
//			for(BusStop bs : busStops) {
//				System.out.println(bs.getName());
//			}
			
//			String hql = "select bl.description from BusLine bl";
//			Query query = session.createQuery(hql);
//			select = (List<String>)query.list();
//			
//			for(String s : select) {
//				System.out.println(s);
//			}
			
//			String hql = "select bls.seqenceNumber, bs.name "
//					+ "from BusLineStop bls, BusLine bl, BusStop bs "
//					+ "where bls.lineId = bl.line and bls.stopId = bs.id and bl.line = :line";
//			Query query = session.createQuery(hql);
//			query.setString("line", "METRO");
//			seqence = (List<Short>)query.list();
//			
//			for (Iterator it = query.iterate(); it.hasNext();) {
//				Object[] row = (Object[]) it.next();
//				System.out.println("seq = " + row[0]);
//				System.out.println("line = " + row[1]);
//			}
			
//			for(Short s : seqence) {
//				System.out.println(s);
//			}
			
//			session.persist();

//			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		HibernateUtil.shutdown();
	}

}
