package complaint.controller.complaint.response;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.complaint.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {
    private long complaintId;
    private Long customerId;
    private Long employeeId;
    private CustomerComplaintMessage customerComplaintMessage;
    private Date submitDate;
    private EmployeeComplaintMessage employeeComplaintMessage;
    private Date considerDate;
    private boolean accepted;
    private Date acceptanceDate;
    private ComplaintStatus status;


    public ComplaintResponse(Complaint complaint) {
//        this.complaintId = complaint.getComplaintId();
//        if(complaint.getCustomer() != null)
//            this.customerId = complaint.getCustomer().getCustomerId();
//        else
//            this.customerId = null;
//        if(complaint.getEmployee() != null)
//            this.employeeId = complaint.getEmployee().getEmployeeId();
//        else
//            this.employeeId = null;
//        //this.customerComplaint = complaint.getCustomerComplaint();
//        this.submitDate = complaint.getSubmitDate();
//        //this.employeeComplaint = complaint.getEmployeeComplaint();
//        this.considerDate = complaint.getConsiderDate();
//        this.accepted = complaint.isAccepted();
//        this.acceptanceDate = complaint.getAcceptanceDate();
//        this.status = complaint.getStatus();
    }

}
