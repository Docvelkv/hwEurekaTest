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

    private void noBookById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format(bookProperties.getNoBookById(), id));
    }

    public List<Book> showAllBooks(){
        return books.findAll();
    }

    public Book showBookById(long bookId){
        if(books.findById(bookId).isEmpty()) noBookById(bookId);
        return books.findById(bookId).get();
    }

    public Book showBookByTitle(String title){
        if(books.findByTitle(title).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Book title %s not found", title));
        }
        return books.findByTitle(title).get();
    }

    public List<Book> showBookByAuthor(String author){
        if(books.findByAuthor(author).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No books found by author %s", author));
        }
        return books.findByAuthor(author);
    }

    public Book createNewBook(Book book){
        return books.save(book);
    }

    public Book updateBook(long bookId, Book newBook){
        if(books.findById(bookId).isPresent()) noBookById(bookId);

        Book book = books.findById(bookId).get();
        book.setAuthor(newBook.getAuthor());
        book.setTitle(newBook.getTitle());
        return books.save(book);
    }

    public void deleteBookById(long bookId){
        if (books.findById(bookId).isEmpty()) noBookById(bookId);
        books.deleteById(bookId);
    }
}
