package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente un livre dans la bibliothèque.
 * Il peut être emprunté par plusieurs personnes à la fois si des copies sont disponibles.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private UUID id;                // Identifiant unique du livre
    private String title;           // Titre du livre
    private String author;          // Auteur du livre
    private short totalPages;       // Nombre total de pages
    private byte loanPeriod;        // Durée de l'emprunt (en jours)
    private double rentalPrice;     // Prix de location du livre
    private Language language;      // Langue du livre
    private short nbCopies;         // Nombre total de copies disponibles
    private boolean borrowable;     // Livre empruntable ou non
    private ArrayList<Person> borrowers = new ArrayList<>(); // Liste des emprunteurs actuels

    
    /**
     * Constructeur simplifié utilisé pour ajouter rapidement un livre avec les valeurs par défaut.
     */
    public Book(String title, String author, short totalPages) {
        this(
            UUID.randomUUID(),
            title,
            author,
            totalPages,
            (byte) 7,            // Période de prêt par défaut : 7 jours
            1.0,                 // Prix de location par défaut
            Language.FR,         // Langue par défaut
            (short) 1,           // Une seule copie par défaut
            true,                // Livre empruntable par défaut
            new ArrayList<>()    // Liste vide d'emprunteurs
        );
    }

    
    /**
     * Calcule la date de retour d'un livre à partir de la date actuelle.
     * @return Date de retour estimée.
     */
    public LocalDate computeReturnDate() {
        return LocalDate.now().plusDays(this.loanPeriod);
    }
}
