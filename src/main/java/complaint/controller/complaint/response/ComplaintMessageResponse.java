package complaint.controller.complaint.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import complaint.model.complaint.enums.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintMessageResponse {

    private Long complaintMessageId;

    private String message;

    private Claim claim;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private MessageType messageType;

    private Decision decision;

    private EmployeeResponse employeeResponse;

}
