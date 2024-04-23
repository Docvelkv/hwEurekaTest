package docvel.readerService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class BooksProvider {

    private final WebClient webClient;

    public BooksProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        webClient = WebClient.builder()
                .baseUrl("http://book-service/books")
                .filter(loadBalancer)
                .build();
    }

    public List<Book> showAllBook(){
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();
    }

    public Book showBookById(long bookId){
        return webClient.get()
                .uri("/{bookId}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    public List<Book> showBookByTitle(String title){
        return webClient.get()
                .uri("/bookTitle/{title}", title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();
    }

    public List<Book> showBookByAuthor(String author){
        return webClient.get()
                .uri("/bookAuthor/{author}", author)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();
    }

    public Book createBook(Book book){
        return webClient.post()
                .uri("/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    public void deleteBook(long id){
        webClient.delete()
                .uri("/delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Book updateBook(long id, Book book){
        return webClient.put()
                .uri("/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
}