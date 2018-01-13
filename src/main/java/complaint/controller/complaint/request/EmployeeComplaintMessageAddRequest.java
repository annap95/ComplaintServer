<<<<<<< HEAD:src/main/java/complaint/controller/complaint/request/EmployeeComplaintAddRequest.java
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
=======
package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmployeeComplaintMessageAddRequest {

    @NotNull
    String message;

    @NotNull
    Claim claim;

    @NotNull
    Decision decision;

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe:src/main/java/complaint/controller/complaint/request/EmployeeComplaintMessageAddRequest.java
