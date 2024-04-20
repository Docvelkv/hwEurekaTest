package docvel.issueService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BookProvider {

    private final WebClient webClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        webClient = WebClient.builder()
                .baseUrl("http://book-service")
                .filter(loadBalancer)
                .build();
    }

    public Book findBookById(long bookId){
        return webClient.get()
                .uri("/books/{bookId}", bookId)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
}
