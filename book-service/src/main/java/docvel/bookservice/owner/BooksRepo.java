package docvel.bookservice.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthor(String authorLastName);
}
