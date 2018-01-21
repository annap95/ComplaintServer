package complaint.controller.user.request;

import complaint.model.user.enums.UserRole;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmployeeUserRequest {

    @NotNull
    Boolean enabled;

    @NotNull
    UserRole userRole;

}
