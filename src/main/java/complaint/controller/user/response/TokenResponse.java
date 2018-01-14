package complaint.controller.user.response;

import complaint.model.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String token;

    private UserRole userRole;

    private Long userId;

    private Long customerId;

    private Long employeeId;

}
