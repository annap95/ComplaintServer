<<<<<<< HEAD
package complaint.service;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.ComplaintDetails;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import complaint.model.user.Employee;

import java.util.List;

public interface ComplaintService {

    void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage);
    void addCustomerComplaintMessage();
    void addEmployeeComplaintMessage();

    List<Complaint> getComplaints();
    Complaint getComplaintById(long complaintId);

//    void addCustomerComplaint(Customer customer, CustomerComplaintMessage customerComplaintMessage);
//    void addEmployeeComplaint(long complaintId, Employee employee, EmployeeComplaintMessage employeeComplaintMessage);
//
//    List<Complaint> getComplaints();
//    Complaint getComplaintById(long complaintId);
}
=======
package complaint.service;

import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ComplaintService {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAuthority('CUSTOMER')") // todo checking customer
    void addCustomerComplaintMessage(long complaintId, CustomerComplaintMessage customerComplaintMessage);

    @PreAuthorize("hasAnyAuthority('CONSULTANT','ADMIN')")
    void addEmployeeComplaintMessage(long complaintId, EmployeeComplaintMessage employeeComplaintMessage);

    @PostFilter("hasAnyAuthority('CONSULTANT','ADMIN') or filterObject.customer.user.userId == authentication.principal.userId")
    List<Complaint> getComplaints();

    @PostFilter("hasAnyAuthority('CONSULTANT','ADMIN') ") // todo checking customer
    Complaint getComplaintById(long complaintId);

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
