package docvel.readerService.owner;

import docvel.readerService.metrix.Metrics;
import docvel.readerService.providers.Book;
import docvel.readerService.providers.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("readers")
public class ReaderController {

    private final ReaderService service;

    //region Readers

    @PostMapping("create")
    public ResponseEntity<Reader> createNewReader(@RequestBody Reader reader){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createNewReader(reader));
    }

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

    @PostMapping("books/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.ok().body(service.createNewBook(book));
    }

    @GetMapping("books")
    public ResponseEntity<List<Book>> showAllBooks(){
        return ResponseEntity.ok().body(service.showAllBooks());
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> showBookById(@PathVariable long bookId){
        return ResponseEntity.ok().body(service.showBookById(bookId));
    }

    @GetMapping("books/ByAuthor/{author}")
    public ResponseEntity<List<Book>> showBookByAuthor(@PathVariable String author){
        return  ResponseEntity.ok().body(service.showBookByAuthor(author));
    }

    @GetMapping("books/ByTitle/{title}")
    public ResponseEntity<List<Book>> showBookByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(service.showBookByTitle(title));
    }

    @PutMapping("books/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable long bookId,
                                           @RequestBody Book book){
        return ResponseEntity.ok().body(service.updateBook(bookId, book));
    }

    @DeleteMapping("books/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable long bookId){
        service.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Issues

    @PostMapping("issues/create/{readerId}/{bookId}")
    public ResponseEntity<Issue> createIssue(@PathVariable long readerId,
                                             @PathVariable long bookId)throws RuntimeException{
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(service.createIssue(readerId, bookId).getId())
                .toUri();
        ResponseEntity<Issue> response = ResponseEntity.created(location).body(service.createIssue(readerId, bookId));
        Metrics.countingTheNumberOfRequests(response.getStatusCode());
        return response;
    }

    @GetMapping("issues")
    public ResponseEntity<List<Issue>> showAllIssues(){
        return ResponseEntity.ok().body(service.showAllIssues());
    }

    @GetMapping("issues/{issueId}")
    public ResponseEntity<Issue> showIssueById(@PathVariable long issueId){
        return ResponseEntity.ok().body(service.showIssueById(issueId));
    }

    @GetMapping("issues/readerName/{readerName}")
    public ResponseEntity<List<Issue>> showIssuesByReaderName(@PathVariable String readerName){
        return ResponseEntity.ok().body(service.showIssuesByReaderName(readerName));
    }

    @GetMapping("issues/bookAuthor/{bookAuthor}")
    public ResponseEntity<List<Issue>> showIssuesByBookAuthor(@PathVariable String bookAuthor){
        return ResponseEntity.ok().body(service.showIssuesByBookAuthor(bookAuthor));
    }

    @GetMapping("issues/bookTitle/{bookTitle}")
    public ResponseEntity<List<Issue>> showIssuesByBookTitle(@PathVariable String bookTitle){
        return ResponseEntity.ok().body(service.showIssuesByBookTitle(bookTitle));
    }

    @PutMapping("issues/returnBook/{issueId}")
    public ResponseEntity<Issue> returnBook(@PathVariable long issueId){
        return ResponseEntity.ok().body(service.returnBook(issueId));
    }
    //endregion
}
