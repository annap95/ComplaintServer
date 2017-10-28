package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_complaint_messages")
public class CustomerComplaintMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_complaint_message_id")
    private long customerComplaintMessageId;

    @Column(name = "message")
    private String message;

    @Column(name = "claim")
    @Enumerated(EnumType.STRING)
    private Claim claim;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

}
