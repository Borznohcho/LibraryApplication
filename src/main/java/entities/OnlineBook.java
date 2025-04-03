package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineBook extends Book {
    
    private String content;
    private static final byte EBOOK_LIMIT = 5;

}
