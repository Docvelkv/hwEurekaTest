package docvel.readerService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class IssueProvider {

    private final WebClient webClient;

    public IssueProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        this.webClient = WebClient.builder()
                .baseUrl("http://issue-service")
                .filter(loadBalancer)
                .build();
    }

    public List<Issue> showAllIssues(){
        return webClient.get()
                .uri("/issues")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public Issue showIssueById(long issueId){
        return webClient.get()
                .uri("http://issue-service/issues/{issueId}", issueId)
                .retrieve()
                .bodyToMono(Issue.class)
                .block();
    }

    public List<Issue> showIssuesByReaderName(String readerName){
        return webClient.get()
                .uri("http://issue-service/issues/readerName/{readerName}", readerName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public List<Issue> showIssuesByBookAuthor(String bookAuthor){
        return webClient.get()
                .uri("http://issue-service/issues/bookAuthor/{bookAuthor}", bookAuthor)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public List<Issue> showIssuesByBookTitle(String bookTitle){
        return webClient.get()
                .uri("http://issue-service/issues/bookTitle/{bookTitle}", bookTitle)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

}