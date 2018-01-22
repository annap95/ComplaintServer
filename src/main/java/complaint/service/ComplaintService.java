package complaint.service;

import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.model.complaint.*;
import complaint.model.user.Customer;
import complaint.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ComplaintService {

    /* validation */

    void validateGettingComplaint(Complaint complaint, User user);


    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, ComplaintMessage complaintMessage);

    // todo checking customer
    void addComplaintMessage(long complaintId, ComplaintMessage complaintMessage);

    Page<Complaint> getComplaintsAsEmployee(Pageable pageable, ComplaintItemRequest complaintItemRequest);

    Page<Complaint> getComplaintsAsCustomer(Pageable pageable, ComplaintItemRequest complaintItemRequest, Customer customer);

    // todo checking customer
    Complaint getComplaintById(long complaintId);

}
