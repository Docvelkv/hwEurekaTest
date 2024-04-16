package docvel.readerService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BooksProvider {

    private final WebClient webClient;

    public BooksProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        webClient = WebClient.builder()
                .filter(loadBalancer)
                .build();
    }

    public List<Book> showAllBook(){
        return webClient.get()
                .uri("http://book-service/books")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();
    }

    public Book showBookById(long bookId){
        return webClient.get()
                .uri("http://book-service/books/bookId/{bookid}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    public Book showBookByTitle(String title){
        return webClient.get()
                .uri("http://book-service/books/bookTitle/{title}", title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    public List<Book> showBookByAuthor(String author){
        return webClient.get()
                .uri("http://book-service/books/bookAuthor/{author}", author)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();
    }


}
