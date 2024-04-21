package docvel.readerServiceTest.owner;

import docvel.readerServiceTest.providers.Book;
import docvel.readerServiceTest.providers.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReaderControllerTest{

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ReadersRepo readers;

    @Test
    void showAllReader() {
        List<Reader> readersFromRepo = readers.findAll();

        List<Reader> readersFromController = webTestClient.get()
                .uri("readers")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reader.class)
                .returnResult()
                .getResponseBody();

        assert readersFromController != null;
        assertEquals(readersFromRepo.size(), readersFromController.size());
        for(Reader readerFromController : readersFromController) {
            boolean resultTest = readersFromRepo.stream()
                    .filter(reader -> Objects.equals(reader.getId(), readerFromController.getId()))
                    .allMatch(reader -> Objects.equals(reader.getName(), readerFromController.getName()));
            assertTrue(resultTest);
        }
    }

    @Test
    void createNewReader() {
        Reader newReader = new Reader("Bill Pouhu");
        Reader createdReader = webTestClient.post()
                .uri("readers/createNewReader")
                .bodyValue(newReader)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();
        System.out.println(createdReader);
        assert createdReader != null;
        assertEquals(createdReader.getName(), newReader.getName());
    }

    @Test
    void deleteReader() {
        Long randomId = new Random().nextLong(1, readers.findAll().size() + 1);

        webTestClient.delete()
                .uri("readers/delete/{readerId}", randomId)
                .exchange()
                .expectStatus().isOk();
        List<Long> listId = readers.findAll().stream()
                .map(Reader::getId)
                .toList();
        System.out.println(listId);
        System.out.println(randomId);
        assertFalse(listId.contains(randomId));
    }

    @Test
    void updateReader() {
        Long randomId = new Random().nextLong(1, readers.findAll().size() + 1);
        Reader newReader = new Reader("Bill Pouhu");
        Reader updatedReader = webTestClient.put()
                .uri("readers/update/{readerId}", randomId)
                .bodyValue(newReader)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();
        System.out.println(updatedReader);
        assert updatedReader != null;
        assertEquals(updatedReader.getId(), randomId);
        assertEquals(updatedReader.getName(), newReader.getName());
    }

    // region Интеграционные тесты
    @Test
    void createBook() {
        String author = "Грин Александр", title = "Алые паруса";
        Book newBook = new Book();
        newBook.setAuthor(author);
        newBook.setTitle(title);
        Book createdBook = webTestClient.post()
                .uri("http://localhost:5551/books/createBook")
                .bodyValue(newBook)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        System.out.println(createdBook);
        assert createdBook != null;
        assertEquals(createdBook.getAuthor(), author);
        assertEquals(createdBook.getTitle(), title);
    }

    @Test
    void createIssue() {
        Long randReaderId = new Random().nextLong(1, readers.findAll().size() + 1);
        int sizeOfListBooks = Objects.requireNonNull(webTestClient.get()
                        .uri("http://localhost:5551/books")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Book.class)
                        .returnResult()
                        .getResponseBody())
                .size();
        Long randBookId = new Random().nextLong(1, sizeOfListBooks + 1);
        Issue addedIssue = webTestClient.post()
                .uri("http://localhost:5553/issues/create/{readerId}/{bookId}", randReaderId, randBookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();
        System.out.println(addedIssue);
        assert addedIssue != null;
        assertEquals(addedIssue.getReader().getId(), randReaderId);
        assertEquals(addedIssue.getBook().getId(), randBookId);
    }
    //endregion
}
