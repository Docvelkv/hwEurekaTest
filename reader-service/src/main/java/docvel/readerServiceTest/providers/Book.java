package docvel.readerServiceTest.providers;

import lombok.Data;

import java.io.Serializable;


@Data
public class Book implements Serializable {

    private long id;

    private String author;

    private String title;
}
