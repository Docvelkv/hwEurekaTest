package docvel.readerService.providers;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Book implements Serializable {

    private long id;

    private String author;

    private String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }
}
