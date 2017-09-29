package complaint.service.impl;

import complaint.dao.complaint.ComplaintDao;
import complaint.dao.complaint.CustomerComplaintDao;
import complaint.dao.complaint.EmployeeComplaintDao;
import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.complaint.EmployeeComplaint;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
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

    public void addCustomerComplaint(Customer customer, CustomerComplaint customerComplaint) {
        customerComplaintDao.persist(customerComplaint);
        complaintDao.persist(Complaint.builder()
            .customer(customer)
            .customerComplaint(customerComplaint)
            .submitDate(new Date())
            .build());
    }

    @Override
    public void addEmployeeComplaint(long complaintId, Employee employee, EmployeeComplaint employeeComplaint) {
        employeeComplaintDao.persist(employeeComplaint);
        // get complaint
        // set
        // update
    }

    @Override
    public List<Complaint> getComplaints() {
        return complaintDao.findAll();
        // todo authorization
    }
}
