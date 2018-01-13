package complaint.controller.complaint;

import complaint.controller.complaint.request.CustomerComplaintAddRequest;
import complaint.controller.complaint.request.EmployeeComplaintAddRequest;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(value = "/complaint/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void addCustomerComplaint(@RequestBody CustomerComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
        CustomerComplaintMessage customerComplaintMessage = null;
        //complaintService.addCustomerComplaint(customer, customerComplaintMessage);
    }

    @RequestMapping(value = "/complaint/{complaintId}/consultant", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('CONSULTANT','ADMIN')")
    public void addEmployeeComplaint(@PathVariable(name = "complaintId") long complaintId,
                                     @RequestBody EmployeeComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeByUser(loggedUser.getUserId());
        EmployeeComplaintMessage employeeComplaintMessage = EmployeeComplaintMessage.builder()
                .decision(request.getDecision())
                .claim(request.getClaim())
                .build();
        //complaintService.addEmployeeComplaint(complaintId, employee, employeeComplaintMessage);
    }

}
