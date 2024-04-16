package docvel.issueService.owner;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.application.variable")
public class IssueProperties {

    private int maxAllowedBooks;
}
