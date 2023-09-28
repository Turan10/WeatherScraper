package utility;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;


public class EntityManagerInit {
    private static EntityManagerFactory emFactory = HibernateConfig.getEntityManagerFactoryConfig();



    private EntityManagerInit() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if(emFactory == null) {
            emFactory = HibernateConfig.getEntityManagerFactoryConfig();
        }
        return emFactory;
    }
}