package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente la bibliothèque avec ses livres et ses membres.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLibrary {

    private String name;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Person> people = new ArrayList<>();

    public static final byte BOOK_LIMIT = 3;

    /**
     * Ajoute un nouveau livre à la bibliothèque.
     * Si un livre avec le même titre et auteur existe déjà, on incrémente nbCopies.
     *
     * @param book Livre à ajouter ou à mettre à jour
     */
    public void addBook(Book book) {
        for (Book existing : books) {
            if (existing.getTitle().equalsIgnoreCase(book.getTitle()) &&
                existing.getAuthor().equalsIgnoreCase(book.getAuthor())) {

                // Livre déjà présent → on augmente le nombre de copies
                short totalCopies = (short) (existing.getNbCopies() + book.getNbCopies());
                existing.setNbCopies(totalCopies);
                System.out.println("Livre déjà existant. Nb de copies mis à jour à " + totalCopies);
                return;
            }
        }

        // Livre nouveau → ajout dans la collection
        books.add(book);
        System.out.println("Livre ajouté à la bibliothèque.");
    }
    

    /**
     * Ajoute un nouveau membre à la bibliothèque.
     * Si une personne avec le même nom est déjà inscrite, on refuse l’ajout.
     *
     * @param person Personne à inscrire
     */
    public void addPerson(Person person) {
        for (Person existing : people) {
            if (existing.getName().equalsIgnoreCase(person.getName())) {
                System.out.println("Cette personne est déjà inscrite.");
                return;
            }
        }

        people.add(person);
        System.out.println("Membre ajouté avec succès.");
    }
    

    /**
     * Recherche les livres dont le titre contient un mot-clé (insensible à la casse).
     *
     * @param title Mot-clé recherché dans le titre
     * @return Liste des livres correspondants
     */
    public ArrayList<Book> findBooksByTitle(String title) {
        ArrayList<Book> found = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                found.add(book);
            }
        }
        return found;
    }

    
    /**
     * Affiche la liste des livres de la bibliothèque.
     */
    public void printBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor() + " (" + book.getNbCopies() + " copies)");
        }
    }

    
    /**
     * Affiche la liste des membres inscrits à la bibliothèque.
     */
    public void printMembers() {
        for (Person person : people) {
            System.out.println(person.getName() + ", inscrit le " + person.getRegistrationDate());
        }
    }
    
    /**
    * Sauvegarde tous les livres de la bibliothèque dans un fichier CSV.
    * @param filename Nom du fichier où sauvegarder (ex. "data/books.csv")
    */
    public void saveBooks(String filename) {
	    try {
	        File file = new File(filename);
	        File directory = new File(file.getParent());

	        // Créer le dossier "data" s’il n’existe pas
	        if (!directory.exists()) {
	            directory.mkdir();
	        }

	        // Créer le fichier "books.csv" s’il n’existe pas
	        if (!file.exists()) {
	            file.createNewFile();
	        }

	        // Écriture des livres
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	            for (Book b : books) {
	                writer.write(b.getId() + "," + escape(b.getTitle()) + "," + escape(b.getAuthor()) + "," +
	                             b.getTotalPages() + "," + b.getLoanPeriod() + "," + b.getRentalPrice() + "," +
	                             b.getLanguage() + "," + b.getNbCopies() + "," + b.isBorrowable());
	                writer.newLine();
	            }
	        }

	        // Message de confirmation + chemin
	        System.out.println(" Livres sauvegardés dans : " + filename);
	        System.out.println(" Fichier créé/écrit à : " + file.getAbsolutePath());

	    } catch (IOException e) {
	        System.out.println(" Erreur lors de la sauvegarde des livres : " + e.getMessage());
	    }
	}

    
   /**
    * Charge les livres depuis un fichier CSV et les ajoute à la bibliothèque.
    * @param filename Nom du fichier CSV à charger (ex. "data/books.csv")
    */
   public void loadBooks(String filename) {
       books.clear(); // Vider la liste avant de recharger
       
       File file = new File(filename);
       if (!file.exists()) {
           System.out.println(" Aucun fichier trouvé pour les livres.");
           return;
       }

       try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");

               UUID id = UUID.fromString(parts[0]);
               String title = unescape(parts[1]);
               String author = unescape(parts[2]);
               short totalPages = Short.parseShort(parts[3]);
               byte loanPeriod = Byte.parseByte(parts[4]);
               double rentalPrice = Double.parseDouble(parts[5]);
               Language language = Language.valueOf(parts[6]);
               short nbCopies = Short.parseShort(parts[7]);
               boolean borrowable = Boolean.parseBoolean(parts[8]);

               Book book = new Book(id, title, author, totalPages, loanPeriod, rentalPrice,
                                    language, nbCopies, borrowable, new ArrayList<>());
               books.add(book);
           }
           System.out.println(" Livres chargés depuis : " + filename);
       } catch (IOException | NumberFormatException e) {
           System.out.println(" Erreur lors du chargement des livres : " + e.getMessage());
       }
   }

   /**
    * Échappe les virgules dans les champs texte.
    */
   private String escape(String input) {
       return input.replace(",", "%2C");
   }

   /**
    * Restaure les virgules échappées dans les champs texte.
    */
   private String unescape(String input) {
       return input.replace("%2C", ",");
   }
    
    
}
