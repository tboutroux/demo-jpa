package fr.epsi.b3devc1;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import fr.epsi.b3devc1.bo.Livre;

public class Main {
    public static void main(String[] args) {

        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
                EntityManager em = emf.createEntityManager()
        ) {

            em.getTransaction().begin();

            // Create a new Livre
            Livre livre = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien");
            em.persist(livre);
            System.out.println(livre.getTitre() + " by " + livre.getAuteur() + " has been added to the database.");

            // Search for a Livre
            Livre searchLivre = em.find(Livre.class, 1);
            System.out.println("Found: " + searchLivre);

            // Update a Livre
            livre.setTitre("Le Hobbit");
            System.out.println("Updated: " + livre);

            // Remove a Livre
            Livre deleteLivre = em.find(Livre.class, 1);
            em.remove(deleteLivre);
            System.out.println("Removed: " + deleteLivre);

            em.getTransaction().commit();
        }
    }
}