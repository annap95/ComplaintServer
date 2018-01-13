<<<<<<< HEAD
package complaint.service.impl;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.ComplaintDetails;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.repository.complaint.ComplaintRepository;
import complaint.repository.complaint.CustomerComplaintMessageRepository;
import complaint.repository.complaint.EmployeeComplaintMessageRepository;
import complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final CustomerComplaintMessageRepository customerComplaintMessageRepository;
    private final EmployeeComplaintMessageRepository employeeComplaintMessageRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                CustomerComplaintMessageRepository customerComplaintMessageRepository,
                                EmployeeComplaintMessageRepository employeeComplaintMessageRepository) {
        this.complaintRepository = complaintRepository;
        this.customerComplaintMessageRepository = customerComplaintMessageRepository;
        this.employeeComplaintMessageRepository = employeeComplaintMessageRepository;
    }

    @Override
    public void addComplaint(Customer customer, ComplaintDetails complaintDetails, CustomerComplaintMessage customerComplaintMessage) {
        customerComplaintMessageRepository.save(customerComplaintMessage);
        complaintRepository.save(Complaint.builder()
            .customer(customer)
            .submitDate(new Date())
            .build());
    }

    @Override
    public void addCustomerComplaintMessage() {
        // na poczatku sprawdzac status

    }

    @Override
    public void addEmployeeComplaintMessage() {
        // na poczatku sprawdzac status

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

    //    public void addCustomerComplaint(Customer customer, CustomerComplaintMessage customerComplaintMessage) {
//        customerComplaintDao.persist(customerComplaintMessage);
////        complaintDao.persist(Complaint.builder()
////            .customer(customer)
////            .customerComplaint(customerComplaintMessage)
////            .submitDate(new Date())
////            .build());
//        // todo status
//    }
//
//    @Override
//    public void addEmployeeComplaint(long complaintId, Employee employee, EmployeeComplaintMessage employeeComplaintMessage) {
//        employeeComplaintDao.persist(employeeComplaintMessage);
//        Complaint complaint = getComplaintById(complaintId);
//        complaint.setConsiderDate(new Date());
//        complaintDao.update(complaint);
//        // todo status
//    }
}
=======
package complaint.service.impl;

import complaint.model.complaint.*;
import complaint.model.complaint.enums.ComplaintStatus;
import complaint.model.user.Customer;
import complaint.repository.complaint.ComplaintDetailsRepository;
import complaint.repository.complaint.ComplaintRepository;
import complaint.repository.complaint.CustomerComplaintMessageRepository;
import complaint.repository.complaint.EmployeeComplaintMessageRepository;
import complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addCustomerComplaintMessage(long complaintId, CustomerComplaintMessage customerComplaintMessage) {
        // todo status, exception
        Complaint complaint = complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        customerComplaintMessage.setComplaint(complaint);
        customerComplaintMessageRepository.save(customerComplaintMessage);
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
    }

    @Override
    public Complaint getComplaintById(long complaintId) {
        return complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
