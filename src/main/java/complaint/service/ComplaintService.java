package complaint.service;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.user.Customer;

import java.util.List;

public interface ComplaintService {
    void addComplaint(Customer customer, CustomerComplaint customerComplaint);
    List<Complaint> getComplaints();
}
