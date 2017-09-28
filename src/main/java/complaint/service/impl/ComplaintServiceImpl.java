package complaint.service.impl;

import complaint.dao.complaint.ComplaintDao;
import complaint.dao.complaint.CustomerComplaintDao;
import complaint.dao.complaint.EmployeeComplaintDao;
import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.user.Customer;
import complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintDao complaintDao;
    private final CustomerComplaintDao customerComplaintDao;
    private final EmployeeComplaintDao employeeComplaintDao;

    @Autowired

    public ComplaintServiceImpl(ComplaintDao complaintDao,
                                CustomerComplaintDao customerComplaintDao,
                                EmployeeComplaintDao employeeComplaintDao) {
        this.complaintDao = complaintDao;
        this.customerComplaintDao = customerComplaintDao;
        this.employeeComplaintDao = employeeComplaintDao;
    }

    public void addComplaint(Customer customer, CustomerComplaint customerComplaint) {
        customerComplaintDao.persist(customerComplaint);
        Complaint complaint = new Complaint();
        complaint.setCustomer(customer);
        complaint.setCustomerComplaint(customerComplaint);
        complaint.setSubmitDate(new Date());
        complaintDao.persist(complaint);  //TODO builder
    }

    @Override
    public List<Complaint> getComplaints() {
        return complaintDao.findAll();
    }
}
