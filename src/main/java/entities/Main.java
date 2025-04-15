package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Programme principal de la bibliothèque.
 */
public class Main {

    public static void main(String[] args) {

	MyLibrary library = new MyLibrary();
	library.setName("Nid des lecteurs");
	
	// Charger les livres existants au démarrage
	library.loadBooks("data/books.csv");

	Scanner scan = new Scanner(System.in);
	int choix;

	do {
	    System.out.println("\nMenu principal");
	    System.out.println("1 - Ajouter un membre");
	    System.out.println("2 - Ajouter un livre");
	    System.out.println("3 - Emprunter un livre");
	    System.out.println("4 - Afficher les statistiques");
	    System.out.println("5 - Sauvegarder les livres");
	    System.out.println("6 - Charger les livres");
	    System.out.println("0 - Quitter");

	    try {
		choix = scan.nextInt();
		scan.nextLine(); // vider le buffer

		switch (choix) {
		case 1:
		    System.out.print("Nom du membre : ");
		    String nom = scan.nextLine();

		    // Vérifier si le nom est déjà dans la liste des membres
		    boolean exists = library.getPeople().stream().anyMatch(p -> p.getName().equalsIgnoreCase(nom));

		    if (exists) {
			System.out.println("Ce membre est déjà inscrit.");
		    } else {
			Person p = new Person(UUID.randomUUID(), nom, LocalDate.now(), new java.util.TreeMap<>());
			library.addPerson(p);
			System.out.println("Membre ajouté.");
		    }
		    break;

		case 2:
		    System.out.print("Titre : ");
		    String title = scan.nextLine();
		    System.out.print("Auteur : ");
		    String author = scan.nextLine();
		    System.out.print("Nombre de pages : ");
		    short pages = scan.nextShort();
		    scan.nextLine(); // vider le tampon

		    System.out.print("Nombre de copies : ");
		    short copies = scan.nextShort();
		    scan.nextLine(); // vider le tampon

		    Book book = new Book(title, author, pages);
		    book.setNbCopies(copies);
		    library.addBook(book);
		    break;

		case 3:
		    System.out.print("Nom du membre : ");
		    String name = scan.nextLine();
		    Person person = library.getPeople().stream().filter(pers -> pers.getName().equalsIgnoreCase(name))
			    .findFirst().orElse(null);

		    if (person == null) {
			System.out.println("Aucun membre trouvé.");
			break;
		    }

		    System.out.print("Titre du livre : ");
		    String bookTitle = scan.nextLine();
		    ArrayList<Book> foundBooks = library.findBooksByTitle(bookTitle);

		    if (foundBooks.isEmpty()) {
			System.out.println("Aucun livre correspondant.");
			break;
		    }

		    Book selectedBook = foundBooks.get(0); // simple sélection
		    try {
			person.borrows(selectedBook);
			System.out.println("Livre emprunté. Retour prévu : " + selectedBook.computeReturnDate());
		    } catch (NotAvailableException e) {
			System.out.println(e.getMessage());
		    }
		    break;

		case 4:
		    System.out.println(" Statistiques :");
		    System.out.println("- Nombre de livres : " + library.getBooks().size());
		    System.out.println("- Nombre de membres : " + library.getPeople().size());

		    // Membres ayant effectué au moins un emprunt
		    long activeMembers = library.getPeople().stream()
		            .filter(pers -> !pers.getLoans().isEmpty()).count();

		    // Livres qui ont au moins un emprunteur
		    long borrowedBooks = library.getBooks().stream()
		            .filter(b -> !b.getBorrowers().isEmpty()).count();

		    // Nombre total de livres en retard
		    long lateBooks = library.getPeople().stream()
		            .mapToLong(pers -> pers.getLateBooks().size()).sum();

		    System.out.println("- Membres actifs : " + activeMembers);
		    System.out.println("- Livres empruntés : " + borrowedBooks);
		    System.out.println("- Livres en retard : " + lateBooks);

		    // Détail par membre des livres en retard
		    System.out.println("\n📌 Détail des livres en retard par membre :");

		    for (Person pers : library.getPeople()) {
		        ArrayList<Book> late = pers.getLateBooks();

		        if (!late.isEmpty()) {
		            System.out.println("- " + pers.getName() + " (" + late.size() + " livre(s) en retard) :");
		            for (Book b : late) {
		                LocalDate dateEmprunt = null;

		                // Chercher la date d'emprunt correspondante
		                for (LocalDate d : pers.getLoans().keySet()) {
		                    if (pers.getLoans().get(d).contains(b)) {
		                        dateEmprunt = d;
		                        break;
		                    }
		                }

		                System.out.println("    • " + b.getTitle() + " (emprunté le : " + dateEmprunt +
		                        ", retour prévu : " + dateEmprunt.plusDays(b.getLoanPeriod()) + ")");
		            }
		        }
		    }
		    break;
		    
		case 5:
		    library.saveBooks("data/books.csv");
		    break;

		case 6:
		    
		    library.loadBooks("data/books.csv");
		    System.out.println("Livres actuellement en mémoire :");
		    library.printBooks(); // Affiche les livres chargés
		    break;
		    
		case 0:
		    System.out.println("Fin du programme.");
		    return;

		default:
		    System.out.println("Choix invalide.");
		}
	    } catch (InputMismatchException e) {
		System.out.println("Veuillez entrer un nombre valide.");
		scan.nextLine(); // vider le buffer
		choix = -1;
	    }
	} while (true);
    }
}
