package complaint.controller.complaint.mapper.impl;

import complaint.controller.complaint.mapper.ComplaintMapper;
import complaint.controller.complaint.response.ComplaintItemResponse;
import complaint.controller.complaint.response.ComplaintMessageResponse;
import complaint.controller.complaint.response.ComplaintResponse;
import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.response.CustomerResponse;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.complaint.Complaint;
import complaint.model.complaint.ComplaintMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComplaintMapperImpl implements ComplaintMapper {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ComplaintItemResponse mapComplaintToComplaintItemResponse(Complaint complaint) {
        ComplaintItemResponse complaintItemResponse = new ComplaintItemResponse();
        complaintItemResponse.setComplaintId(complaint.getComplaintId());
        complaintItemResponse.setStatus(complaint.getStatus());
        complaintItemResponse.setSubmitDate(complaint.getSubmitDate());
        complaintItemResponse.setName(complaint.getCustomer().getName());
        complaintItemResponse.setSurname(complaint.getCustomer().getSurname());
        return complaintItemResponse;
    }

    @Override
    public ComplaintMessageResponse mapComplaintMessageToComplaintMessageResponse(ComplaintMessage complaintMessage) {
        ComplaintMessageResponse complaintMessageResponse = new ComplaintMessageResponse();
        complaintMessageResponse.setComplaintMessageId(complaintMessage.getComplaintMessageId());
        complaintMessageResponse.setClaim(complaintMessage.getClaim());
        complaintMessageResponse.setMessage(complaintMessage.getMessage());
        complaintMessageResponse.setDate(complaintMessage.getDate());
        complaintMessageResponse.setMessageType(complaintMessage.getMessageType());
        complaintMessageResponse.setDecision(complaintMessage.getDecision());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        if(complaintMessage.getEmployee() != null)
            complaintMessageResponse.setEmployeeResponse(userMapper
                .mapEmployeeToEmployeeResponse(complaintMessage.getEmployee(), employeeResponse));
        return complaintMessageResponse;
    }

    @Override
    public ComplaintResponse mapComplaintToComplaintResponse(Complaint complaint) {
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setComplaintId(complaint.getComplaintId());
        complaintResponse.setSubmitDate(complaint.getSubmitDate());
        complaintResponse.setConsiderDate(complaint.getConsiderDate());
        complaintResponse.setStatus(complaint.getStatus());
        complaintResponse.setComplaintMessages(complaint.getComplaintMessages()
                                                    .stream()
                                                    .map(this::mapComplaintMessageToComplaintMessageResponse)
                                                    .collect(Collectors.toList()));
        CustomerResponse customerResponse = new CustomerResponse();
        complaintResponse.setCustomerResponse(userMapper
                .mapCustomerToCustomerResponse(complaint.getCustomer(), customerResponse));
        complaintResponse.setProductDescription(complaint.getComplaintDetails().getProductDescription());
        complaintResponse.setInvoiceNumber(complaint.getComplaintDetails().getInvoiceNumber());
        complaintResponse.setPurchaseDate(complaint.getComplaintDetails().getPurchaseDate());
        complaintResponse.setPrice(complaint.getComplaintDetails().getPrice());
        complaintResponse.setIban(complaint.getComplaintDetails().getIban());
        return complaintResponse;
    }

}
