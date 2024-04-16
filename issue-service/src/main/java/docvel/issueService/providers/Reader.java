package docvel.issueService.providers;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Reader implements Serializable {

    private long id;

    private String name;
}
