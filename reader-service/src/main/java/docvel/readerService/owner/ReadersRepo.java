package docvel.readerService.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadersRepo extends JpaRepository<Reader, Long> {

    Optional<Reader> findReaderByName(String name);

}
