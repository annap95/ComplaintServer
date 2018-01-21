package complaint.service;

import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.model.complaint.*;
import complaint.model.user.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ComplaintService {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, ComplaintMessage complaintMessage);

    // todo checking customer
    void addComplaintMessage(long complaintId, ComplaintMessage complaintMessage);

    Page<Complaint> getComplaintsAsEmployee(Pageable pageable, ComplaintItemRequest complaintItemRequest);

    Page<Complaint> getComplaintsAsCustomer(Pageable pageable, ComplaintItemRequest complaintItemRequest, Customer customer);

    @PostFilter("hasAnyAuthority('CONSULTANT','ADMIN') ") // todo checking customer
    Complaint getComplaintById(long complaintId);

}
