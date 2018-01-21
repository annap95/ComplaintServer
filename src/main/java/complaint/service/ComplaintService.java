package complaint.service;

import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ComplaintService {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAuthority('CUSTOMER')") // todo checking customer
    void addCustomerComplaintMessage(long complaintId, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAnyAuthority('CONSULTANT','ADMIN')")
    void addEmployeeComplaintMessage(long complaintId, EmployeeComplaintMessage employeeComplaintMessage);

    Page<Complaint> getComplaints(Pageable pageable, ComplaintItemRequest complaintItemRequest);

    @PostFilter("hasAnyAuthority('CONSULTANT','ADMIN') ") // todo checking customer
    Complaint getComplaintById(long complaintId);

}
