package complaint.controller.complaint;

import complaint.controller.complaint.mapper.ComplaintMapper;
import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.controller.complaint.response.ComplaintResponse;
import complaint.controller.pagination.PaginationRequest;
import complaint.controller.pagination.PaginationResponse;
import complaint.model.complaint.Complaint;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class ComplaintReadController {
    private final UserService userService;
    private final ComplaintService complaintService;
    private final ComplaintMapper complaintMapper;

    @Autowired
    public ComplaintReadController(UserService userService,
                                   ComplaintService complaintService,
                                   ComplaintMapper complaintMapper) {
        this.userService = userService;
        this.complaintService = complaintService;
        this.complaintMapper = complaintMapper;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponse getComplaints(@RequestBody PaginationRequest<ComplaintItemRequest> paginationRequest) {
        ComplaintItemRequest complaintItemRequest = paginationRequest.getFilterOptions();
        if(complaintItemRequest == null)
            complaintItemRequest = new ComplaintItemRequest();

        Page<Complaint> page =
                complaintService.getComplaints(paginationRequest.mapToPageable(ComplaintItemRequest.class), complaintItemRequest);

        return PaginationResponse.builder()
                .totalItems(page.getTotalElements())
                .items(page.getContent()
                        .stream()
                        .map(complaintMapper::mapComplaintToComplaintItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    @RequestMapping(value = "/complaint/{complaintId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ComplaintResponse getComplaint(@PathVariable(name = "complaintId") long complaintId, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        return new ComplaintResponse(complaint);
    }
}
