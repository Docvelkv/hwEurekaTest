package docvel.readerService.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadersRepo extends JpaRepository<Reader, Long> {

    Optional<Reader> findReaderByName(String name);

}
