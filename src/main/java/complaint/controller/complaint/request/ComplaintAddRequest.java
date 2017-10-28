package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
public class ComplaintAddRequest {

    /* details */

    @NotNull
    String productDescription;

    @NotNull
    String invoiceNumber;

    @NotNull
    Date purchaseDate;

    @NotNull
    Double price;

    @NotNull
    String iban;

    /* message */

    @NotNull
    String message;

    @NotNull
    Claim claim;

}
