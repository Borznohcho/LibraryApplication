package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Book {
    
    private UUID id;
    private String title;
    private String author;
    private short totalPages;
    private byte loanPeriod;
    private double rentalPrice;
    private Language language;
    private short nbCopies;
    private boolean borrowable;
    private ArrayList<Person> borrowers = new ArrayList<>();
    
    public Book(String title,String author,short totalPages) {
	this(UUID.randomUUID(),title,author,totalPages,(byte)7,1,Language.FR,(short)1,true,new ArrayList<Person>());
	
    }
    
    public LocalDate computeReturnDate(){
	// TODO
	return null;
	
    }
    

}
