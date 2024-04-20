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
        service.createBook(new Book("Блок Александр", "Двенадцать"));
        service.createBook(new Book("Гоголь Николай", "Мёртвые души"));
        service.createBook(new Book("Айтматов Чингиз", "Плаха"));
        service.createBook(new Book("Бунин Иван", "Тёмные аллеи"));
        service.createBook(new Book("Маркес Габриэль Гарсиа", "Сто лет одиночества"));
        service.createBook(new Book("Некрасов Николай", "Кому на Руси жить хорошо"));
        service.createBook(new Book("Пушкин Александр", "Евгений Онегин"));
        service.createBook(new Book("Антуан де Сент-Экзюпери", "Маленький принц"));
        service.createBook(new Book("Твардовский Александр", "Василий Тёркин"));
        service.createBook(new Book("Чехов Антон", "Вишнёвый сад"));
        service.createBook(new Book("Гоголь Николай", "Вечера на хуторе близ Диканьки"));
    }

    @GetMapping
    public ResponseEntity<List<Book>> showAllBooks(){
        return ResponseEntity.ok().body(service.showAllBooks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> showBookById(@PathVariable long id){
        return ResponseEntity.ok().body(service.showBookById(id));
    }

    @GetMapping("bookTitle/{title}")
    public ResponseEntity<List<Book>> showBookByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(service.showBookByTitle(title));
    }

    @GetMapping("bookAuthor/{author}")
    public ResponseEntity<List<Book>> showBookByAuthor(@PathVariable String author){
        return ResponseEntity.ok().body(service.showBookByAuthor(author));
    }

    @PostMapping("createBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.ok().body(service.createBook(book));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable long id){
        service.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id,
                                           @RequestBody Book book){
        return ResponseEntity.ok().body(service.updateBook(id, book));
    }
}
