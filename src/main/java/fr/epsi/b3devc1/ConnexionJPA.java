package fr.epsi.b3devc1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnexionJPA {

    public static void Main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa")) {
            System.out.println("EntityManagerFactory created");

            EntityManager em = emf.createEntityManager();
            System.out.println("EntityManager closed");
        }


    }
}
