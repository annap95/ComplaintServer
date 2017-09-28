package complaint.controller.complaint.request;

import complaint.model.complaint.enums.Claim;

import java.util.Date;

/**
 * Created by anna on 22.09.17.
 */
public class CustomerComplaintAddRequest {
    private String productDescription;
    private String invoiceNumber;
    private Date purchaseDate;
    private Double price;
    private String complaintReason;
    private Claim claim;

    public CustomerComplaintAddRequest() {
    }

    public CustomerComplaintAddRequest(String productDescription, String invoiceNumber,
                                       Date purchaseDate, Double price,
                                       String complaintReason, Claim claim) {
        this.productDescription = productDescription;
        this.invoiceNumber = invoiceNumber;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.complaintReason = complaintReason;
        this.claim = claim;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComplaintReason() {
        return complaintReason;
    }

    public void setComplaintReason(String complaintReason) {
        this.complaintReason = complaintReason;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}
