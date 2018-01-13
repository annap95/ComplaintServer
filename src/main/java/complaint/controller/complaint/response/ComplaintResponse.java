<<<<<<< HEAD
package complaint.controller.complaint.response;

import complaint.model.complaint.Complaint;
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
//        //this.customerComplaintMessage = complaint.getCustomerComplaintMessage();
//        this.submitDate = complaint.getSubmitDate();
//        //this.employeeComplaintMessage = complaint.getEmployeeComplaintMessage();
//        this.considerDate = complaint.getConsiderDate();
//        this.accepted = complaint.isAccepted();
//        this.acceptanceDate = complaint.getAcceptanceDate();
//        this.status = complaint.getStatus();
    }

}
=======
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

    private Date submitDate;
    private Date considerDate;
    private ComplaintStatus status;


//    private Long customerId;
//    private Long employeeId;
//    private CustomerComplaintMessage customerComplaintMessage;
//    private EmployeeComplaintMessage employeeComplaintMessage;


    public ComplaintResponse(Complaint complaint) {
        this.complaintId = complaint.getComplaintId();
        this.complaintDetails = complaint.getComplaintDetails();
        this.customerId = complaint.getCustomer().getCustomerId();

        this.customerComplaintMessages = complaint.getCustomerComplaintMessages();

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
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
