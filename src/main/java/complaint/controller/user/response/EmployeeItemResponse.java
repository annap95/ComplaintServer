package complaint.controller.user.response;

import complaint.model.user.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeItemResponse {

    private long employeeId;

    private String name;

    private String surname;

    private UserRole userRole;

    private String email;

    private Boolean enabled;

}
