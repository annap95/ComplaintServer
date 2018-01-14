package complaint.controller.complaint.response;

import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class ComplaintResponse implements Serializable {
    private long complaintId;
    private ComplaintDetails complaintDetails;
    private long customerId;

    private List<CustomerComplaintMessage> customerComplaintMessages;
    private List<EmployeeComplaintMessage> employeeComplaintMessages;

    private Date submitDate;
    private Date considerDate;
    private ComplaintStatus status;


//    private Long customerId;
//    private Long employeeId;
//    private EmployeeComplaintMessage employeeComplaintMessage;


    public ComplaintResponse(Complaint complaint) {
        this.complaintId = complaint.getComplaintId();
        this.complaintDetails = complaint.getComplaintDetails();
        this.customerId = complaint.getCustomer().getCustomerId();

        this.customerComplaintMessages = complaint.getCustomerComplaintMessages();
        this.employeeComplaintMessages = complaint.getEmployeeComplaintMessages();

        this.submitDate = complaint.getSubmitDate();
        this.considerDate = complaint.getConsiderDate();
        this.status = complaint.getStatus();
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
//        //this.employeeComplaint = complaint.getEmployeeComplaint();
    }

}
