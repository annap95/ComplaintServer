package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CustomerComplaintMessageAddRequest {

    @NotNull
    String message;

    @NotNull
    Claim claim;

}
