package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
public class CustomerComplaintMessageAddRequest {

    @NotNull
    String complaintReason;

    @NotNull
    Claim claim;

}
