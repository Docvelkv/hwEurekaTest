package docvel.readerServiceTest.owner;

import docvel.readerServiceTest.providers.Book;
import docvel.readerServiceTest.providers.BooksProvider;
import docvel.readerServiceTest.providers.Issue;
import docvel.readerServiceTest.providers.IssueProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReadersRepo readers;
    private final BooksProvider booksProvider;
    private final IssueProvider issuesProvider;

    //region Reader
    private void noReaderById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Reader with id %d not found", id));
    }

    public List<Reader> showAllReader(){
        return readers.findAll();
    }

    public Reader showReaderById(long readerId){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        return readers.findById(readerId).get();
    }

    public Reader showReaderByName(String name){
        if(readers.findReaderByName(name).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Reader named %s not found", name));
        }
        return readers.findReaderByName(name).get();
    }

    public Reader createNewReader(Reader reader){
        return readers.save(reader);
    }

    public void deleteReader(long readerId){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        readers.deleteById(readerId);
    }

    public Reader updateReader(long readerId, Reader newReader){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        Reader reader = readers.findById(readerId).get();
        reader.setName(newReader.getName());
        return readers.save(reader);
    }
    //endregion

    //region Book
    public Book createNewBook(Book newBook){
        return booksProvider.createBook(newBook);
    }

    public List<Book> showAllBooks(){
        return booksProvider.showAllBook();
    }

    public Book showBookById(long id){
        return booksProvider.showBookById(id);
    }

    public List<Book> showBookByAuthor(String author){
        return booksProvider.showBookByAuthor(author);
    }

    public List<Book> showBookByTitle(String title){
        return booksProvider.showBookByTitle(title);
    }

    public void deleteBook(long id){
        booksProvider.deleteBook(id);
    }

    public Book updateBook(long id, Book book){
        return booksProvider.updateBook(id, book);
    }
    //endregion

    //region Issue
    public Issue createIssue(long readerId, long bookId){
        return issuesProvider.createIssue(readerId, bookId);
    }

    public List<Issue> showAllIssues(){
        return issuesProvider.showAllIssues();
    }

    public Issue showIssueById(long issueId){
        return issuesProvider.showIssueById(issueId);
    }

    public List<Issue> showIssuesByReaderName(String readerName){
        return issuesProvider.showIssuesByReaderName(readerName);
    }

    public List<Issue> showIssuesByBookAuthor(String bookAuthor){
        return issuesProvider.showIssuesByBookAuthor(bookAuthor);
    }

    public List<Issue> showIssuesByBookTitle(String bookTitle){
        return issuesProvider.showIssuesByBookTitle(bookTitle);
    }

    public Issue returnBook(long issueId){
        return issuesProvider.returnBook(issueId);
    }
    //endregion
}
