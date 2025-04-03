package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

	/*
	 * 76. //Affichage du menu 
	 * 77. écrire "Menu principal 
	 * 1 - Ajouter un membre 
	 * 2 - Ajouter un livre 
	 * 3 - Emprunter un livre 
	 * 4 - Afficher les statistiques 
	 * 0 – Quitter"
	 * 
	 */
	
	
	MyLibrary library = new MyLibrary();
	library.setName("Nid des lecteurs");
	Scanner scan = new Scanner(System.in);
	int choix = -1;
	
	String menuPrincipal = "Menu principal \n"
		+ "\t1 - Ajouter un membre \n"
		+ "\t2 - Ajouter un livre \n"
		+ "\t3 - Emprunter un livre \n"
		+ "\t4 - Afficher les statistiques \n"
		+ "\t0 – Quitter";
	
	//Affichage du menu
	do {
	    try {
		System.out.println(menuPrincipal);

		//Lecture du choix
		choix = scan.nextInt();
		
		// Valider entrée utilisateur (entier compris entre 0-4)
		if(choix < 0 && choix > 4) {
		    
		    throw new RangeException("Choix non valide :" + choix + "([]0,1,2,3,4)");
		    
		}
		
	    } catch (InputMismatchException e) {

		System.out.println("Veuillez entrer un nombre parmi ceux proposés");
		scan.nextLine(); // Vider le tampon
	    } catch(RangeException e) {
		System.out.println("Veuillez entrer un nombre parmi ceux proposés");
		scan.nextLine(); // Vider le tampon
		choix = -1;
		
	    }
	} while (choix == -1);
	
	//Traitement du choix
	switch (choix) {
	case 1:
	    
	    //Demander le nom
	    System.out.println("Quel est le nom du membre ?");
	    scan.nextLine(); // Vider le tampon
	    String nom = scan.nextLine();
	   
	    //Créer une personne
	    Map<LocalDate,ArrayList<Book>> loans = new TreeMap<>();
	    Person p = new Person(UUID.randomUUID(), nom, LocalDate.now() , loans);
	    
	    //Ajouter à la library
	    library.addPerson(p);
	    
	    break;

	default:
	    System.out.println("Fin du programme.");
	    System.exit(0);
	}
	
	System.out.println(library);
	
	
    }

}
