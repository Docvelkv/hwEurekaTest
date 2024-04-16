package docvel.issueService.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("issues")
public class IssueController {

    private final IssueService service;

    @GetMapping
    public ResponseEntity<List<Issue>> showAllIssues(){
        return ResponseEntity.ok().body(service.showAllIssues());
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> showIssueById(@PathVariable long id){
        return ResponseEntity.ok().body(service.showIssueById(id));
    }

    @GetMapping("readerName/{readerName}")
    public ResponseEntity<List<Issue>> showIssuesByReaderName(@PathVariable String readerName){
        return ResponseEntity.ok().body(service.showIssueByReaderName(readerName));
    }

    @GetMapping("bookAuthor/{bookAuthor}")
    public ResponseEntity<List<Issue>> showIssuesByBookAuthor(@PathVariable String bookAuthor){
        return ResponseEntity.ok().body(service.showIssueByBookAuthor(bookAuthor));
    }

    @GetMapping("bookTitle/{bookTitle}")
    public ResponseEntity<List<Issue>> showIssuesByBookTitle(@PathVariable String bookTitle){
        return ResponseEntity.ok().body(service.showIssuesByBookTitle(bookTitle));
    }

    @PostMapping("{readerId}/{bookId}")
    public ResponseEntity<Issue> createNewIssue(@PathVariable long readerId,
                                                @PathVariable long bookId){
        return ResponseEntity.ok().body(service.createNewIssue(readerId, bookId));
    }

    @PutMapping("{issueId}")
    public ResponseEntity<Issue> returnOfBook(@PathVariable long issueId){
        return ResponseEntity.ok().body(service.returnOfBook(issueId));
    }
}
