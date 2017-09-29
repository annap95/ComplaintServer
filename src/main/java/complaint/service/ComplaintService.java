package complaint.service;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.complaint.EmployeeComplaint;
import complaint.model.user.Customer;
import complaint.model.user.Employee;

import java.util.List;

public interface ComplaintService {

    void addCustomerComplaint(Customer customer, CustomerComplaint customerComplaint);
    void addEmployeeComplaint(long complaintId, Employee employee, EmployeeComplaint employeeComplaint);

    List<Complaint> getComplaints();
}
