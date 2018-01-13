package complaint.controller.user.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CustomerRegisterRequest {

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

}
