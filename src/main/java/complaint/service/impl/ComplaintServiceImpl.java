package complaint.service.impl;

import complaint.model.complaint.*;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.complaint.enums.ComplaintStatus;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.repository.complaint.ComplaintDetailsRepository;
import complaint.repository.complaint.ComplaintRepository;
import complaint.repository.complaint.CustomerComplaintMessageRepository;
import complaint.repository.complaint.EmployeeComplaintMessageRepository;
import complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintDetailsRepository complaintDetailsRepository;
    private final CustomerComplaintMessageRepository customerComplaintMessageRepository;
    private final EmployeeComplaintMessageRepository employeeComplaintMessageRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                ComplaintDetailsRepository complaintDetailsRepository,
                                CustomerComplaintMessageRepository customerComplaintMessageRepository,
                                EmployeeComplaintMessageRepository employeeComplaintMessageRepository) {
        this.complaintRepository = complaintRepository;
        this.complaintDetailsRepository = complaintDetailsRepository;
        this.customerComplaintMessageRepository = customerComplaintMessageRepository;
        this.employeeComplaintMessageRepository = employeeComplaintMessageRepository;
    }

    @Override
    public void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage) {
        Complaint savedComplaint = complaintRepository.saveAndFlush(Complaint.builder()
            .customer(customer)
            .submitDate(new Date())
            .status(ComplaintStatus.SUBMITTED)
            .build());
        complaintDetails.setComplaint(savedComplaint);
        complaintDetailsRepository.save(complaintDetails);
        customerComplaintMessage.setComplaint(savedComplaint);
        customerComplaintMessageRepository.save(customerComplaintMessage);
    }

    @Override
    public void addCustomerComplaintMessage(long complaintId) {
        // todo status, exception
        Complaint complaint = complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

    }

    @Override
    public void addEmployeeComplaintMessage(long complaintId, EmployeeComplaintMessage employeeComplaintMessage) {
        // todo status, exception
        Complaint complaint = complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        employeeComplaintMessage.setComplaint(complaint);
        employeeComplaintMessageRepository.save(employeeComplaintMessage);
    }

    @Override
    public List<Complaint> getComplaints() {
        return complaintRepository.findAll();
        // todo authorization
    }

    @Override
    public Complaint getComplaintById(long complaintId) {
        return complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

}
