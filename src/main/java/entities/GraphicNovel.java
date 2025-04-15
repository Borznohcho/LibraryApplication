package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Représente une bande dessinée (hérite de Book).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicNovel extends Book {
    private String cartoonist; // Nom du dessinateur
    private boolean color;     // true si en couleur, false si en noir et blanc
}
