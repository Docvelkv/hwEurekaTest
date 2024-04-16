package docvel.issueService.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuesRepo extends JpaRepository<Issue, Long> {

    List<Issue> findIssuesByBookTitle(String bookTitle);
    List<Issue> findIssuesByBookAuthor(String bookAuthor);
    List<Issue> findIssueByReaderName(String readerName);
}
