package complaint.controller.complaint.mapper;

import complaint.controller.complaint.response.ComplaintItemResponse;
import complaint.controller.complaint.response.ComplaintMessageResponse;
import complaint.controller.complaint.response.ComplaintResponse;
import complaint.model.complaint.Complaint;
import complaint.model.complaint.ComplaintMessage;

public interface ComplaintMapper {

    ComplaintItemResponse mapComplaintToComplaintItemResponse(Complaint complaint);

    ComplaintMessageResponse mapComplaintMessageToComplaintMessageResponse(ComplaintMessage complaintMessage);

    ComplaintResponse mapComplaintToComplaintResponse(Complaint complaint);

}
