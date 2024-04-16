package docvel.issueService.providers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private long id;

    private String author;

    private String title;


}
