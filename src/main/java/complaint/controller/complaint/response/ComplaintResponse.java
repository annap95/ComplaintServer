package complaint.controller.complaint.response;

import complaint.model.complaint.Complaint;
import complaint.model.complaint.CustomerComplaint;
import complaint.model.complaint.EmployeeComplaint;
import complaint.model.complaint.enums.ComplaintStatus;

import java.util.Date;

/**
 * Created by anna on 25.09.17.
 */
public class ComplaintResponse {
    private long complaintId;
    private Long customerId;
    private Long employeeId;
    private CustomerComplaint customerComplaint;
    private Date submitDate;
    private EmployeeComplaint employeeComplaint;
    private Date considerDate;
    private boolean accepted;
    private Date acceptanceDate;
    private ComplaintStatus status;

    public ComplaintResponse() {
    }

    public ComplaintResponse(long complaintId, long customerId, long employeeId,
                             CustomerComplaint customerComplaint, Date submitDate,
                             EmployeeComplaint employeeComplaint, Date considerDate,
                             boolean accepted, Date acceptanceDate,
                             ComplaintStatus status) {
        this.complaintId = complaintId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.customerComplaint = customerComplaint;
        this.submitDate = submitDate;
        this.employeeComplaint = employeeComplaint;
        this.considerDate = considerDate;
        this.accepted = accepted;
        this.acceptanceDate = acceptanceDate;
        this.status = status;
    }

    public ComplaintResponse(Complaint complaint) {
        this.complaintId = complaint.getComplaintId();
        if(complaint.getCustomer() != null)
            this.customerId = complaint.getCustomer().getCustomerId();
        else
            this.customerId = null;
        if(complaint.getEmployee() != null)
            this.employeeId = complaint.getEmployee().getEmployeeId();
        else
            this.employeeId = null;
        //this.customerComplaint = complaint.getCustomerComplaint();
        this.submitDate = complaint.getSubmitDate();
        //this.employeeComplaint = complaint.getEmployeeComplaint();
        this.considerDate = complaint.getConsiderDate();
        this.accepted = complaint.isAccepted();
        this.acceptanceDate = complaint.getAcceptanceDate();
        this.status = complaint.getStatus();
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public CustomerComplaint getCustomerComplaint() {
        return customerComplaint;
    }

    public void setCustomerComplaint(CustomerComplaint customerComplaint) {
        this.customerComplaint = customerComplaint;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public EmployeeComplaint getEmployeeComplaint() {
        return employeeComplaint;
    }

    public void setEmployeeComplaint(EmployeeComplaint employeeComplaint) {
        this.employeeComplaint = employeeComplaint;
    }

    public Date getConsiderDate() {
        return considerDate;
    }

    public void setConsiderDate(Date considerDate) {
        this.considerDate = considerDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }
}
