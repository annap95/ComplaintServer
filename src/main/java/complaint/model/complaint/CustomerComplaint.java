package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "customer_complaints")
public class CustomerComplaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_complaint_id")
    private long customerComplaintId;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "complaint_reason")
    private String complaintReason;

    @Column(name = "claim")
    @Enumerated(EnumType.STRING)
    private Claim claim;


    public CustomerComplaint(String productDescription, String invoiceNumber,
                             Date purchaseDate, Double price, String complaintReason,
                             Claim claim) {
        this.productDescription = productDescription;
        this.invoiceNumber = invoiceNumber;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.complaintReason = complaintReason;
        this.claim = claim;
    }
}
