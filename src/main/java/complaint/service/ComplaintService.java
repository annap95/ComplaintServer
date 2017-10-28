package complaint.service;

import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ComplaintService {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addCustomerComplaintMessage(long complaintId);

    @PreAuthorize("hasAnyAuthority('CONSULTANT','ADMIN')")
    void addEmployeeComplaintMessage(long complaintId, EmployeeComplaintMessage employeeComplaintMessage);

    List<Complaint> getComplaints();
    Complaint getComplaintById(long complaintId);

}
