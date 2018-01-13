<<<<<<< HEAD
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
=======
package complaint.model.complaint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaints_details")
public class ComplaintDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_detail_id")
    private long complaintDetailId;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
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

    @Column(name = "iban")
    private String iban;

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
