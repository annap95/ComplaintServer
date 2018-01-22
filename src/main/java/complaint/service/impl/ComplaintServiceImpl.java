package complaint.service.impl;

import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.model.complaint.*;
import complaint.model.complaint.enums.ComplaintStatus;
import complaint.model.complaint.enums.Decision;
import complaint.model.complaint.enums.MessageType;
import complaint.model.user.Customer;
import complaint.model.user.User;
import complaint.repository.complaint.ComplaintDetailsRepository;
import complaint.repository.complaint.ComplaintMessageRepository;
import complaint.repository.complaint.ComplaintRepository;
import complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintDetailsRepository complaintDetailsRepository;
    private final ComplaintMessageRepository complaintMessageRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                ComplaintDetailsRepository complaintDetailsRepository,
                                ComplaintMessageRepository complaintMessageRepository) {
        this.complaintRepository = complaintRepository;
        this.complaintDetailsRepository = complaintDetailsRepository;
        this.complaintMessageRepository = complaintMessageRepository;
    }

    @Override
    public void validateGettingComplaint(Complaint complaint, User user) {
        if(!user.isEmployee() && complaint.getCustomer().getUser().getUserId() != user.getUserId())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validateAddingCustomerComplaintMessage(Long complaintId, User user) {
        Complaint complaint = complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        if(complaint.getCustomer().getUser().getUserId() != user.getUserId())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validateAddingEmployeeComplaintMessage(Long complaintId, User user) {
        if(!user.isEmployee())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void addComplaint(Customer customer, ComplaintDetails complaintDetails, ComplaintMessage complaintMessage) {
        Complaint savedComplaint = complaintRepository.saveAndFlush(Complaint.builder()
            .customer(customer)
            .submitDate(new Date())
            .status(ComplaintStatus.SUBMITTED)
            .currentCustomerClaim(complaintMessage.getClaim())
            .build());
        complaintDetails.setComplaint(savedComplaint);
        complaintDetailsRepository.save(complaintDetails);
        complaintMessage.setComplaint(savedComplaint);
        complaintMessageRepository.save(complaintMessage);
    }

    @Override
    public void addComplaintMessage(long complaintId, ComplaintMessage complaintMessage) {
        Complaint complaint = complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaintMessage.setComplaint(complaint);
        ComplaintMessage savedComplaintMessage = complaintMessageRepository.saveAndFlush(complaintMessage);
        processComplaint(complaint, savedComplaintMessage);
    }

    @Override
    public Page<Complaint> getComplaintsAsEmployee(Pageable pageable, ComplaintItemRequest complaintItemRequest) {
        return complaintRepository.findAllAsEmployee(pageable, complaintItemRequest);
    }

    @Override
    public Page<Complaint> getComplaintsAsCustomer(Pageable pageable, ComplaintItemRequest complaintItemRequest,
                                                  Customer customer) {
        return complaintRepository.findAllAsCustomer(pageable, complaintItemRequest, customer);
    }

    @Override
    public Complaint getComplaintById(long complaintId) {
        return complaintRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    @Override
    public Complaint processComplaint(Complaint complaint, ComplaintMessage complaintMessage) {
        if(complaint.getStatus() == ComplaintStatus.ACCEPTED)
            return complaint;
        /* status submitted or waiting */
        if(complaintMessage.getMessageType() == MessageType.EMPLOYEE) {
            if(complaintMessage.getClaim() == complaint.getCurrentCustomerClaim()
                    && complaintMessage.getDecision() == Decision.ACCEPT) {
                complaint.setStatus(ComplaintStatus.ACCEPTED);
                complaint.setCurrentEmployeeClaim(complaintMessage.getClaim());
                complaint.setConsiderDate(new Date());
            }
            else {
                complaint.setStatus(ComplaintStatus.WAITING);
                complaint.setCurrentEmployeeClaim(complaintMessage.getClaim());
            }
        }
        else {
            if(complaintMessage.getClaim() == complaint.getCurrentEmployeeClaim()) {
                complaint.setStatus(ComplaintStatus.ACCEPTED);
                complaint.setCurrentCustomerClaim(complaintMessage.getClaim());
            }
            else
                complaint.setCurrentCustomerClaim(complaintMessage.getClaim());
        }
        return complaintRepository.saveAndFlush(complaint);
    }
}
