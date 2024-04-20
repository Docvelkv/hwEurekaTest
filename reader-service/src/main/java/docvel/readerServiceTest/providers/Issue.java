package docvel.readerServiceTest.providers;

import docvel.readerServiceTest.owner.Reader;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Issue implements Serializable {

    private long id;

    private Reader reader;

    private Book book;

    private LocalDate dateOfIssue = LocalDate.now();

    private LocalDate dateOfReturn;
}