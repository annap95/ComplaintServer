package complaint.controller.complaint.mapper.impl;

import complaint.controller.complaint.mapper.ComplaintMapper;
import complaint.controller.complaint.response.ComplaintItemResponse;
import complaint.model.complaint.Complaint;
import org.springframework.stereotype.Service;

@Service
public class ComplaintMapperImpl implements ComplaintMapper {

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
}
