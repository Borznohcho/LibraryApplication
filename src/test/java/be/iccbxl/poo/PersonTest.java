package be.iccbxl.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import entities.Book;
import entities.Person;

public class PersonTest {
    
    @Test
    public void emprunter() {
	
	//Préparation
	Map<LocalDate,ArrayList<Book>> loans = new TreeMap<LocalDate,ArrayList<Book>>();
	Person person = new Person(UUID.randomUUID(),LocalDate.of(2020, 10, 25),loans);
	Book book = new Book("Une vie", "Guy de Maupassant", (short)210);
	
	//Utilisation
	person.emprunter(book);
	
	//Vérification
	
    }

}
