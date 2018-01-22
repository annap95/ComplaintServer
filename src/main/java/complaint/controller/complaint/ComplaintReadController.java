package complaint.controller.complaint;

import complaint.controller.complaint.mapper.ComplaintMapper;
import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.controller.complaint.response.ComplaintResponse;
import complaint.controller.pagination.PaginationRequest;
import complaint.controller.pagination.PaginationResponse;
import complaint.model.complaint.Complaint;
import complaint.model.user.Customer;
import complaint.model.user.User;
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
    public PaginationResponse getComplaints(@RequestBody PaginationRequest<ComplaintItemRequest> paginationRequest,
                                            Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();

        ComplaintItemRequest complaintItemRequest = paginationRequest.getFilterOptions();
        if(complaintItemRequest == null)
            complaintItemRequest = new ComplaintItemRequest();

        Page<Complaint> page;
        if(loggedUser.isEmployee())
            page = complaintService.getComplaintsAsEmployee(paginationRequest.mapToPageable(ComplaintItemRequest.class), complaintItemRequest);
        else {
            Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
            page = complaintService.getComplaintsAsCustomer(paginationRequest.mapToPageable(ComplaintItemRequest.class), complaintItemRequest, customer);
        }

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
        User loggedUser = (User) authentication.getPrincipal();
        Complaint complaint = complaintService.getComplaintById(complaintId);
        complaintService.validateGettingComplaint(complaint, loggedUser);
        return complaintMapper.mapComplaintToComplaintResponse(complaint);
    }
}
