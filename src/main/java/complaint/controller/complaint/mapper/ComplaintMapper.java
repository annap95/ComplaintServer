package complaint.controller.complaint.mapper;

import complaint.controller.complaint.response.ComplaintItemResponse;
import complaint.model.complaint.Complaint;

public interface ComplaintMapper {

    ComplaintItemResponse mapComplaintToComplaintItemResponse(Complaint complaint);
}
