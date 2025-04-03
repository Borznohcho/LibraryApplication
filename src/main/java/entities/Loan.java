package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Loan {
    
    private LocalDate loanDate;
    private Person person;
    private ArrayList<Book> books;

}
