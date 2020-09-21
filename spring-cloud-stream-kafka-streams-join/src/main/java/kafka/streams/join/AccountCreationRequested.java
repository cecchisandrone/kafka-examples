package kafka.streams.join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountCreationRequested {

    private String requestId;

    private String firstName;

    private String lastName;

    private String email;
}
