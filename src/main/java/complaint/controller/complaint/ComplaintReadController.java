<<<<<<< HEAD
package complaint.controller.complaint;

import complaint.controller.complaint.response.ComplaintResponse;
import complaint.model.complaint.Complaint;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ComplaintReadController {
    private final UserService userService;
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintReadController(UserService userService, ComplaintService complaintService) {
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ComplaintResponse> getComplaints(Authentication authentication) {
        return complaintService.getComplaints()
                .stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/complaint/{complaintId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ComplaintResponse getComplaint(@PathVariable(name = "complaintId") long complaintId, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        return new ComplaintResponse(complaint);
    }
}
=======
package complaint.controller.complaint;

import complaint.controller.complaint.response.ComplaintResponse;
import complaint.model.complaint.Complaint;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ComplaintReadController {
    private final UserService userService;
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintReadController(UserService userService, ComplaintService complaintService) {
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ComplaintResponse> getComplaints(Authentication authentication) {
        return complaintService.getComplaints()
                .stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/complaint/{complaintId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ComplaintResponse getComplaint(@PathVariable(name = "complaintId") long complaintId, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        return new ComplaintResponse(complaint);
    }
}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
