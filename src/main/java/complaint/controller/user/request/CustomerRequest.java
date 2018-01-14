package complaint.controller.user.request;

import lombok.Value;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Value
public class CustomerRequest {

    @NotNull
    String name;

    @NotNull
    String surname;

    @NotNull
    String streetName;

    @NotNull
    String streetNumber;

    @NotNull
    String postalCode;

    @NotNull
    String town;

    @NotNull
    String phone;

    @NotNull
    @AssertTrue
    boolean dataProcessingPermission;

}
