package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente un membre inscrit à la bibliothèque.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private UUID id; // Identifiant unique
    private String name; // Nom de la personne
    private LocalDate registrationDate; // Date d’inscription
    private Map<LocalDate, ArrayList<Book>> loans = new TreeMap<>(); // Emprunts classés par date

    /**
     * Renvoie les livres empruntés actuellement en retard.
     */
    public ArrayList<Book> getLateBooks() {
        ArrayList<Book> lateBooks = new ArrayList<>();
        for (Map.Entry<LocalDate, ArrayList<Book>> entry : loans.entrySet()) {
            LocalDate date = entry.getKey();
            for (Book book : entry.getValue()) {
                if (date.plusDays(book.getLoanPeriod()).isBefore(LocalDate.now())) {
                    lateBooks.add(book);
                }
            }
        }
        return lateBooks;
    }

    /**
     * Enregistre un emprunt pour un livre, si possible.
     */
    public void borrows(Book book) {
        if (!book.isBorrowable()) {
            throw new NotAvailableException("Ce livre ne peut pas être emprunté.");
        }

        if (book.getBorrowers().size() >= book.getNbCopies()) {
            throw new NotAvailableException("Toutes les copies sont empruntées.");
        }

        ArrayList<Book> todayBooks = loans.getOrDefault(LocalDate.now(), new ArrayList<>());
        todayBooks.add(book);
        loans.put(LocalDate.now(), todayBooks);
        book.getBorrowers().add(this);
    }
}
