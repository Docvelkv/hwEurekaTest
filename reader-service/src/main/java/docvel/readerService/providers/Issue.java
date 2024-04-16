package docvel.readerService.providers;

import docvel.readerService.owner.Reader;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Issue {

    private long id;

    private Reader reader;

    private Book book;

    private LocalDate dateOfIssue = LocalDate.now();

    private LocalDate dateOfReturn;
}