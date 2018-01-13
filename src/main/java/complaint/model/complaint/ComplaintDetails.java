package complaint.model.complaint;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaints_details")
public class ComplaintDetails implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "price")
    private Double price;

}
