package complaint.controller.complaint;

import complaint.controller.complaint.request.CustomerComplaintAddRequest;
import complaint.controller.complaint.request.EmployeeComplaintAddRequest;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.complaint.EmployeeComplaint;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
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

    @RequestMapping(value = "/complaint/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomerComplaint(@RequestBody CustomerComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
        CustomerComplaint customerComplaint = CustomerComplaint.builder()
                .productDescription(request.getProductDescription())
                .invoiceNumber(request.getInvoiceNumber())
                .purchaseDate(request.getPurchaseDate())
                .price(request.getPrice())
                .complaintReason(request.getComplaintReason())
                .claim(request.getClaim())
                .build();
        complaintService.addCustomerComplaint(customer, customerComplaint);
    }

    @RequestMapping(value = "/complaint/{complaintId}/consultant", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployeeComplaint(@PathVariable(name = "complaintId") long complaintId,
                                     @RequestBody EmployeeComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeByUser(loggedUser.getUserId());
        EmployeeComplaint employeeComplaint = EmployeeComplaint.builder()
                .decision(request.getDecision())
                .claim(request.getClaim())
                .justification(request.getJustification())
                .build();
        complaintService.addEmployeeComplaint(complaintId, employee, employeeComplaint);
    }

}
