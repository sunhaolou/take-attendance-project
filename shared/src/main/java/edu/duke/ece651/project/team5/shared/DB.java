// package edu.duke.ece651.project.team5.admin;
package edu.duke.ece651.project.team5.shared;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DB {
    private static DB instance;

    private EntityManager em;
    private EntityManagerFactory emf;

    // Private constructor prevents instantiation from other classes
    private DB() {
        try {
            emf = Persistence.createEntityManagerFactory("ece651");

        } catch (Exception e) {
            System.err.println("Failed to initialize database connection: " + e.getMessage());
        }
    }

    // Public method to get the single instance of the class
    public static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB();
                }
            }
        }
        return instance;
    }

    public static EntityManagerFactory getEmf() {
        return getInstance().emf; // Ensures single instance and connection usage
    }

}
