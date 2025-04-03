package entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    
    
    private UUID id;
    private String name;
    private LocalDate registrationDate;
    private Map<LocalDate,ArrayList<Book>> loans = new TreeMap<LocalDate,ArrayList<Book>>();
    
    
    public ArrayList<Book> getLateBooks(){
	//TODO
	return null;
    }
    
    public void emprunter(Book book) {
	//TODO
    }


}
