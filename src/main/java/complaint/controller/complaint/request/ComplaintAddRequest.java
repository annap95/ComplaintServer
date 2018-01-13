<<<<<<< HEAD:src/main/java/complaint/controller/complaint/request/CustomerComplaintAddRequest.java
package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
public class CustomerComplaintAddRequest {

    @NotNull
    String productDescription;

    @NotNull
    String invoiceNumber;

    @NotNull
    Date purchaseDate;

    @NotNull
    Double price;

    @NotNull
    String complaintReason;

    @NotNull
    Claim claim;

}
=======
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

    /* permission */

    @NotNull
    boolean dataProcessingPermission;

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe:src/main/java/complaint/controller/complaint/request/ComplaintAddRequest.java
