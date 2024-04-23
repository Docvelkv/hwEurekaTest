package docvel.issueService.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import docvel.issueService.providers.BookProvider;
import docvel.issueService.providers.ReaderProvider;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssuesRepo repository;
    private final IssueProperties properties;
    private final ReaderProvider readerProvider;
    private final BookProvider bookProvider;

    private boolean maxAllowedBooks(long readerId){
        return repository.findAll()
                .stream()
                .filter(issue -> issue.getReader().getId() == readerId)
                .toList()
                .size() > properties.getMaxAllowedBooks();
    }

    public Issue createIssue(long readerId, long bookId) throws ResponseStatusException{
        if(readerProvider.findReaderById(readerId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Читатель с id %d не найден", readerId));
        }

        if(bookProvider.findBookById(bookId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Book with id %d not found", bookId));
        }

        if(maxAllowedBooks(readerId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("The reader with id %d has more than %d books." +
                                    "The issuance of new books is prohibited",
                            readerId, properties.getMaxAllowedBooks()));
        }

        Issue issue = new Issue();
        issue.setReader(readerProvider.findReaderById(readerId));
        issue.setBook(bookProvider.findBookById(bookId));
        issue.setDateOfIssue(LocalDate.now());

        return repository.save(issue);
    }

    public Issue showIssueById(long issueId){
        if(repository.findById(issueId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Record with id %d not found", issueId));
        }
        return repository.findById(issueId).get();
    }

    public List<Issue> showIssueByReaderName(String readerName){
        return repository.findAll().stream()
                .filter(issue -> issue.getReader().getName().contains(readerName))
                .toList();
    }

    public List<Issue> showIssueByBookAuthor(String author){
        return repository.findAll().stream()
                .filter(issue -> issue.getBook().getAuthor().contains(author))
                .toList();
    }

    public List<Issue> showIssuesByBookTitle(String title){
        return repository.findAll().stream()
                .filter(issue -> issue.getBook().getTitle().contains(title))
                .toList();
    }

    public List<Issue> showAllIssues(){
        return repository.findAll();
    }

    public Issue returnOfBook(long issueId){
        long bookId = repository.findById(issueId).get().getBook().getId();
        if(repository.findById(issueId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Record with id %d not found", issueId));
        }
        if(repository.findById(issueId).get().getDateOfReturn() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Book with id %d - returned", bookId));
        }
        Issue issue = repository.findById(issueId).get();
        issue.setDateOfReturn(LocalDate.now());
        return repository.save(issue);
    }
}