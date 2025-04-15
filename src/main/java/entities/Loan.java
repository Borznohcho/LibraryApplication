package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente un emprunt effectué par un membre à une date précise.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private LocalDate loanDate;
    private Person person;
    private ArrayList<Book> books;
}
