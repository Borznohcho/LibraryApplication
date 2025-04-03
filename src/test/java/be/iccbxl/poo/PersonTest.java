package be.iccbxl.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import entities.Book;
import entities.NotAvailableException;
import entities.Person;

public class PersonTest {
    
    @Test
    public void borrows() {
	
	//Préparation
	Map<LocalDate,ArrayList<Book>> loans = new TreeMap<LocalDate,ArrayList<Book>>();
	Person bob = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25),loans);
	Book book = new Book("Une vie", "Guy de Maupassant", (short)210);
	
	//Utilisation
	bob.borrows(book);
	
	//Vérification

	ArrayList<Book> emprunts =  bob.getLoans().get(LocalDate.now());
	assertEquals(1, emprunts.size());
    }
    
    @Test
    public void emprunterWithNoCopiesLeftShouldFail() {
	
	//Préparation
	Map<LocalDate,ArrayList<Book>> loans = new TreeMap<LocalDate,ArrayList<Book>>();
	Person bob = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25),loans);
	Person lydia = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25),loans);
	Book book = new Book("Une vie", "Guy de Maupassant", (short)210);
	
	//Utilisation
	bob.borrows(book);
	assertThrows(NotAvailableException.class,() ->  {
	lydia.borrows(book);
	});
	//Vérification

	ArrayList<Book> emprunts =  lydia.getLoans().get(LocalDate.now());
	assertEquals(0, emprunts.size());
    }

}
