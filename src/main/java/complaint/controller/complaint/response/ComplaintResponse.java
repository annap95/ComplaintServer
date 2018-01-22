package complaint.controller.complaint.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import complaint.controller.user.response.CustomerResponse;
import complaint.model.complaint.enums.ComplaintStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintResponse {

    private Long complaintId;

    private List<ComplaintMessageResponse> complaintMessages;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date submitDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date considerDate;

    private ComplaintStatus status;

    private CustomerResponse customerResponse;

    /* details */

    private String productDescription;

    private String invoiceNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    private Double price;

    private String iban;

}
