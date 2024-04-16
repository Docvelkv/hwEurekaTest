package docvel.issueService.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepo extends JpaRepository<Issue, Long> {
}
