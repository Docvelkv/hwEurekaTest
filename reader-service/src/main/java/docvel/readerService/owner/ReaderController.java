package docvel.readerService.owner;

import docvel.readerService.providers.Book;
import docvel.readerService.providers.BooksProvider;
import docvel.readerService.providers.Issue;
import docvel.readerService.providers.IssueProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("readers")
public class ReaderController {

    private final ReaderService service;
    private final BooksProvider booksProvider;
    private final IssueProvider issueProvider;

    //region Readers
    @GetMapping
    public ResponseEntity<List<Reader>> showAllReader(){
        return ResponseEntity.ok().body(service.showAllReader());
    }

    @GetMapping("{readerId}")
    public ResponseEntity<Reader> showReaderById(@PathVariable long readerId){
        return ResponseEntity.ok().body(service.showReaderById(readerId));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Reader> showReaderByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.showReaderByName(name));
    }

    @PostMapping("createNewReader")
    public ResponseEntity<Reader> createNewReader(@RequestBody Reader reader){
        return ResponseEntity.ok().body(service.createNewReader(reader));
    }

    @DeleteMapping("delete/{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable long readerId){
        service.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{readerId}")
    public ResponseEntity<Reader> updateReader(@PathVariable long readerId,
                                               @RequestBody Reader reader){
        return ResponseEntity.ok().body(service.updateReader(readerId, reader));
    }
    //endregion

    //region Books

    @GetMapping("books")
    public ResponseEntity<List<Book>> showAllBooks(){
        return ResponseEntity.ok().body(booksProvider.showAllBook());
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> showBookById(@PathVariable long bookId){
        return ResponseEntity.ok().body(booksProvider.showBookById(bookId));
    }

    @GetMapping("booksByAuthor/{author}")
    public ResponseEntity<List<Book>> showBookByAuthor(@PathVariable String author){
        return  ResponseEntity.ok().body(booksProvider.showBookByAuthor(author));
    }
    //endregion

    //region Issues
    @GetMapping("issues")
    public ResponseEntity<List<Issue>> showAllIssues(){
        return ResponseEntity.ok().body(issueProvider.showAllIssues());
    }

    @GetMapping("issuesById/{issueId}")
    public ResponseEntity<Issue> showIssueById(@PathVariable long issueId){
        return ResponseEntity.ok().body(issueProvider.showIssueById(issueId));
    }

    @GetMapping("issuesByReaderName/{readerName}")
    public ResponseEntity<List<Issue>> showIssuesByReaderName(@PathVariable String readerName){
        return ResponseEntity.ok().body(issueProvider.showIssuesByReaderName(readerName));
    }

    @GetMapping("issuesByBookAuthor/{bookAuthor}")
    public ResponseEntity<List<Issue>> showIssuesByBookAuthor(@PathVariable String bookAuthor){
        return ResponseEntity.ok().body(issueProvider.showIssuesByBookAuthor(bookAuthor));
    }

    @GetMapping("issuesByBookTitle/{bookTitle}")
    public ResponseEntity<List<Issue>> showIssuesByBookTitle(@PathVariable String bookTitle){
        return ResponseEntity.ok().body(issueProvider.showIssuesByBookAuthor(bookTitle));
    }
    //endregion
}
