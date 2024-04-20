package docvel.issueService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReaderProvider {

    private final WebClient webClient;

    public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        webClient = WebClient.builder()
                .baseUrl("http://reader-service")
                .filter(loadBalancer)
                .build();
    }

    public Reader findReaderById(long readerId){
        return webClient.get()
                .uri("/readers/{readerId}", readerId)
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
    }
}
