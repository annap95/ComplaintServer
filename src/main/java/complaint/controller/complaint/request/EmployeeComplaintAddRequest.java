package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmployeeComplaintAddRequest {

    @NotNull
    Decision decision;

    @NotNull
    Claim claim;

    @NotNull
    String justification;

}
