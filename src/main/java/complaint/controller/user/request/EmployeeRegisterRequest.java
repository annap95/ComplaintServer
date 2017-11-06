package complaint.controller.user.request;

import complaint.model.user.enums.UserRole;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmployeeRegisterRequest {

    @NotNull
    UserRole userRole;

    @NotNull
    String name;

    @NotNull
    String surname;

}
