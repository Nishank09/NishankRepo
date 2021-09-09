package in.co.rays.proj3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * The Class HibDataSource.
 * @author Iterator
 * @version 1.0
 */
public class HIBdatasource {

	/** The session factory. */
	public static SessionFactory sessionfactory = null;
	

    /**
     * Get the instance of Session Factory.
     *
     * @return sessionFactory
     */
	public static SessionFactory getsessionfactory(){
		if(sessionfactory==null){
			
			sessionfactory	= new Configuration().configure().buildSessionFactory();
		}
		return sessionfactory; 	
	}
	
	/**
     * Get Session by SessionFactory.
     *
     * @return session
     */
	public static Session getsession(){
		Session session= getsessionfactory().openSession();
		return session;
	}
	
	/**
     * Get Session by SessionFactory.
     *
     * @param session the session
     */
	public static void closesession(Session session){
		
		if(session !=null){
			session.close();
		}
	}
}
