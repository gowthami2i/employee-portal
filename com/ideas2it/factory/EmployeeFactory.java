package com.ideas2it.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;

public class EmployeeFactory {

    private static SessionFactory factory;
    private static Logger logger = LoggerFactory.getLogger(EmployeeFactory.class);

    public static SessionFactory getEmployeeFactory() {

        try {

            factory = new Configuration().
                             configure().
                             addPackage("com.ideas2it.model").
                             addAnnotatedClass(Trainer.class).
                             addAnnotatedClass(Trainee.class).buildSessionFactory();
        } catch (Throwable ex) {

            logger.error("Failed to create sessoionFactory object"+"{}", ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
    }
   

}


