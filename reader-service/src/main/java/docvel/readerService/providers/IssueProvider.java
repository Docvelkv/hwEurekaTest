package docvel.readerService.providers;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class IssueProvider {

    private final WebClient webClient;

    public IssueProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancer) {
        this.webClient = WebClient.builder()
                .baseUrl("http://issue-service/issues")
                .filter(loadBalancer)
                .build();
    }

    public List<Issue> showAllIssues(){
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public Issue showIssueById(long issueId){
        return webClient.get()
                .uri("/{issueId}", issueId)
                .retrieve()
                .bodyToMono(Issue.class)
                .block();
    }

    public List<Issue> showIssuesByReaderName(String readerName){
        return webClient.get()
                .uri("/readerName/{readerName}", readerName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public List<Issue> showIssuesByBookAuthor(String bookAuthor){
        return webClient.get()
                .uri("/bookAuthor/{bookAuthor}", bookAuthor)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public List<Issue> showIssuesByBookTitle(String bookTitle){
        return webClient.get()
                .uri("/bookTitle/{bookTitle}", bookTitle)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Issue>>() {})
                .block();
    }

    public Issue createIssue(long readerId, long bookId) throws ResponseStatusException {
        return webClient.post()
                .uri("/create/{readerId}/{bookId}", readerId, bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Issue.class)
                .block();
    }

    public Issue returnBook(long issueId){
        Issue issue = new Issue();
        try {
            issue = webClient.put()
                    .uri("/returnBook/{issueId}", issueId)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Issue.class)
                    .block();
        }catch (ResponseStatusException e){
            System.out.println(e.getMessage());
        }
        return issue;
    }
}