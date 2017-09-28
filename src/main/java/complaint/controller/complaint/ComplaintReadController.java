package complaint.controller.complaint;

import complaint.controller.complaint.response.ComplaintResponse;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anna on 18.09.17.
 */
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
}
