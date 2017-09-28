package complaint.controller.complaint;

import complaint.controller.complaint.request.CustomerComplaintAddRequest;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.user.Customer;
import complaint.model.user.User;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComplaintWriteController {

    private final UserService userService;
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintWriteController(UserService userService, ComplaintService complaintService) {
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addComplaint(@RequestBody CustomerComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
        complaintService.addComplaint(customer, new CustomerComplaint());
        return "added";
    }
}
