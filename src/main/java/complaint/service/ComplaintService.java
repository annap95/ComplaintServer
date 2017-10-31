package complaint.service;

import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ComplaintService {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAuthority('CUSTOMER')") // todo checking customer
    void addCustomerComplaintMessage(long complaintId, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAnyAuthority('CONSULTANT','ADMIN')")
    void addEmployeeComplaintMessage(long complaintId, EmployeeComplaintMessage employeeComplaintMessage);

    // filter
    List<Complaint> getComplaints();

    // check authorization
    Complaint getComplaintById(long complaintId);

}
