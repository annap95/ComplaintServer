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
