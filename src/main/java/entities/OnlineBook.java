package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente un livre numérique (hérite de Book).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineBook extends Book {
    private String content; // Contenu du livre numérique
    private static final byte EBOOK_LIMIT = 5; // Limite d'emprunts simultanés
}
