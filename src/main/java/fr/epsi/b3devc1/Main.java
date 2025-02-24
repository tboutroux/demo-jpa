package fr.epsi.b3devc1;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import fr.epsi.b3devc1.bo.Livre;
import fr.epsi.b3devc1.bo.Client;
import fr.epsi.b3devc1.bo.Emprunt;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
                EntityManager em = emf.createEntityManager()
        ) {

            em.getTransaction().begin();

//             Create a new Livre
//            Livre livre = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien");
//            em.persist(livre);
//            System.out.println(livre.getTitre() + " by " + livre.getAuteur() + " has been added to the database.");
//
//             Search for a Livre
//            Livre searchLivre = em.find(Livre.class, 1);
//            System.out.println("Found: " + searchLivre);
//
//            // Update a Livre
//            livre.setTitre("Le Hobbit");
//            System.out.println("Updated: " + livre);
//
//            // Remove a Livre
//            Livre deleteLivre = em.find(Livre.class, 1);
//            em.remove(deleteLivre);
//            System.out.println("Removed: " + deleteLivre);

            // Add a new Emprunt
            Client client = em.find(Client.class, 5);
            if (client == null) {
                client = new Client("Doe", "John");
                em.persist(client);
                System.out.println("New client added: " + client);
            }

            Livre livre = em.find(Livre.class, 1);
            if (livre == null) {
                System.out.println("Livre not found!");
                em.getTransaction().rollback();
            } else {
                System.out.println("Livre found: " + livre);

                // Créer un nouvel emprunt en utilisant le livre existant
                Emprunt emprunt = new Emprunt("2021-09-01", "2021-09-30", client, List.of(livre));
                client.getEmprunts().add(emprunt);  // Associe l'emprunt au client

                // Persister uniquement l'emprunt (le livre est déjà en base)
                em.persist(emprunt);
                System.out.println("Emprunt added: " + emprunt);

                em.getTransaction().commit();
            }

            // Get all Emprunts and their Livres
            List<Emprunt> emprunts = em.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
            for (Emprunt e : emprunts) {
                System.out.println("Emprunt: " + e);
                for (Livre l : e.getLivres()) {
                    System.out.println("  Livre: " + l);
                }
            }
        }
    }
}