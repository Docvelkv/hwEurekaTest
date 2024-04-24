package docvel.readerService;

import docvel.readerService.owner.Reader;
import docvel.readerService.owner.ReaderController;
import docvel.readerService.providers.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ReaderServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ReaderServiceApplication.class, args);
        ReaderController contextController = context.getBean(ReaderController.class);


//        contextController.createBook(new Book("Блок Александр", "Двенадцать"));
//        contextController.createBook(new Book("Гоголь Николай", "Мёртвые души"));
//        contextController.createBook(new Book("Айтматов Чингиз", "Плаха"));
//        contextController.createBook(new Book("Бунин Иван", "Тёмные аллеи"));
//        contextController.createBook(new Book("Маркес Габриэль Гарсиа", "Сто лет одиночества"));
//        contextController.createBook(new Book("Некрасов Николай", "Кому на Руси жить хорошо"));
//        contextController.createBook(new Book("Пушкин Александр", "Евгений Онегин"));
//        contextController.createBook(new Book("Антуан де Сент-Экзюпери", "Маленький принц"));
//        contextController.createBook(new Book("Твардовский Александр", "Василий Тёркин"));
//        contextController.createBook(new Book("Чехов Антон", "Вишнёвый сад"));
//        contextController.createBook(new Book("Гоголь Николай", "Вечера на хуторе близ Диканьки"));

        contextController.createNewReader(new Reader("Иван"));
        contextController.createNewReader(new Reader("Пётр"));
        contextController.createNewReader(new Reader("Николай"));
        contextController.createNewReader(new Reader("Елена"));
        contextController.createNewReader(new Reader("Мария"));
        contextController.createNewReader(new Reader("Ольга"));
    }
}
