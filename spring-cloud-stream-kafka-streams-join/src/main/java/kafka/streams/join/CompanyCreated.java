package kafka.streams.join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CompanyCreated {

    private String requestId;

    private Long id;

    private String name;
}
