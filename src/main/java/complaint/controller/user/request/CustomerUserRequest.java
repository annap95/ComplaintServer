package complaint.controller.user.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CustomerUserRequest {

    @NotNull
    Boolean enabled;

}
