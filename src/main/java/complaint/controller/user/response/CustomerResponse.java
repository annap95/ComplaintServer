package complaint.controller.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

    private long customerId;

    private String name;

    private String surname;

    private String streetName;

    private String streetNumber;

    private String postalCode;

    private String town;

    private String phone;

}
