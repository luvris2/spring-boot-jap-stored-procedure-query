package org.example.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit"; // replace with your persistence unit name

    private static final EntityManagerFactory emFactory;
    static {
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
}