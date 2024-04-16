package docvel.bookservice.owner;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService service;

    @PostConstruct
    public void fillingLibrary(){
        service.createNewBook(new Book("Блок Александр", "Двенадцать"));
        service.createNewBook(new Book("Гоголь Николай", "Мёртвые души"));
        service.createNewBook(new Book("Айтматов Чингиз", "Плаха"));
        service.createNewBook(new Book("Бунин Иван", "Тёмные аллеи"));
        service.createNewBook(new Book("Маркес Габриэль Гарсиа", "Сто лет одиночества"));
        service.createNewBook(new Book("Некрасов Николай", "Кому на Руси жить хорошо"));
        service.createNewBook(new Book("Пушкин Александр", "Евгений Онегин"));
        service.createNewBook(new Book("Антуан де Сент-Экзюпери", "Маленький принц"));
        service.createNewBook(new Book("Твардовский Александр", "Василий Тёркин"));
        service.createNewBook(new Book("Чехов Антон", "Вишнёвый сад"));
        service.createNewBook(new Book("Гоголь Николай", "Вечера на хуторе близ Диканьки"));
    }

    @GetMapping
    public ResponseEntity<List<Book>> showAllBooks(){
        return ResponseEntity.ok().body(service.showAllBooks());
    }

    @GetMapping("bookId/{id}")
    public ResponseEntity<Book> showBookById(@PathVariable long id){
        return ResponseEntity.ok().body(service.showBookById(id));
    }

    @GetMapping("bookTitle/{title}")
    public ResponseEntity<Book> showBookByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(service.showBookByTitle(title));
    }

    @GetMapping("bookAuthor/{author}")
    public ResponseEntity<List<Book>> showBookByAuthor(@PathVariable String author){
        return ResponseEntity.ok().body(service.showBookByAuthor(author));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable long id){
        service.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("createNewBook")
    public ResponseEntity<Book> createNewBook(@RequestBody Book book){
        return ResponseEntity.ok().body(service.createNewBook(book));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id,
                                           @RequestBody Book book){
        return ResponseEntity.ok().body(service.updateBook(id, book));
    }
}
