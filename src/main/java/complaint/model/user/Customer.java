package complaint.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "town")
    private String town;

    @Column(name = "phone")
    private String phone;

    @Column(name = "iban")
    private String iban;

    @Column(name = "data_processing_permission")
    private boolean dataProcessingPermission;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Customer(String name, String surname, String streetName, String streetNumber,
                    String postalCode, String town, String phone, String iban,
                    boolean dataProcessingPermission, User user) {
        this.name = name;
        this.surname = surname;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.town = town;
        this.phone = phone;
        this.iban = iban;
        this.dataProcessingPermission = dataProcessingPermission;
        this.user = user;
    }
}