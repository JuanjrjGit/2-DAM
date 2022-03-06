/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadormp3;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Daniel
 */
public class HibernateUtil {
 private static org.hibernate.SessionFactory sessionFactory;

    public static synchronized void buildSessionFactory() {
        if(sessionFactory == null){
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure();
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
    }

    public static org.hibernate.SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            buildSessionFactory();
        }
        
        return sessionFactory;
    }

    //este metodo cierra la sesion que tenemos con la BD
    public static void closeSessionFactory(){
        if((sessionFactory != null) && (sessionFactory.isClosed() == false)){
            sessionFactory.close();
        }
    }
    
    public static void openSessionAndBindToThread () {
        Session session = sessionFactory . openSession ();
        ThreadLocalSessionContext . bind ( session );
    }
    
    public static void closeSessionAndUnbindFromThread () {
        Session session =
        ThreadLocalSessionContext . unbind ( sessionFactory );
        
        if( session != null ){
            session . close ();
        }
    }
}
