package docvel.bookservice.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BooksRepo books;
    private final BookProperties bookProperties;

    //region Private Methods
    private void noBookById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format(bookProperties.getNoBookById(), id));
    }

    private List<Book> findByAuthor(String author){
        return showAllBooks().stream()
                .filter(book -> book.getAuthor().contains(author))
                .toList();
    }

    private List<Book> findByTitle(String title){
        return showAllBooks().stream()
                .filter(book -> book.getTitle().contains(title))
                .toList();
    }
    //endregion

    //region CRUD
    public List<Book> showAllBooks(){
        return books.findAll();
    }

    public Book showBookById(long bookId){
        if(books.findById(bookId).isEmpty()) noBookById(bookId);
        return books.findById(bookId).get();
    }

    public List<Book> showBookByTitle(String title){
        if(findByTitle(title).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Book title %s not found", title));
        }
        return findByTitle(title);
    }

    public List<Book> showBookByAuthor(String author){
        if(findByAuthor(author).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No books found by author %s", author));
        }
        return findByAuthor(author);
    }

    public Book createBook(Book book){
        return books.save(book);
    }

    public Book updateBook(long bookId, Book newBook){
        if(books.findById(bookId).isEmpty()) noBookById(bookId);

        Book book = books.findById(bookId).get();
        book.setAuthor(newBook.getAuthor());
        book.setTitle(newBook.getTitle());
        return books.save(book);
    }

    public void deleteBookById(long bookId){
        if (books.findById(bookId).isEmpty()) noBookById(bookId);
        books.deleteById(bookId);
    }
    //endregion
}
