package docvel.issueService.owner;

import docvel.issueService.providers.Book;
import docvel.issueService.providers.Reader;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JdbcType(JsonJdbcType.class)
    @Column(name = "reader")
    private Reader reader;

    @JdbcType(JsonJdbcType.class)
    @Column(name = "book")
    private Book book;

    @Column(name = "dateOfIssue")
    private LocalDate dateOfIssue = LocalDate.now();

    @Column(name = "dateOfReturn")
    private LocalDate dateOfReturn;
}
