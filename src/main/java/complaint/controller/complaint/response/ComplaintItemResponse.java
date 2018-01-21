package complaint.controller.complaint.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import complaint.model.complaint.enums.ComplaintStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintItemResponse {

    private Long complaintId;

    private ComplaintStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date submitDate;

    private String name;

    private String surname;

}
