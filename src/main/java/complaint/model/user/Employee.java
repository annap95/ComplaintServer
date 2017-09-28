package complaint.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by anna on 18.09.17.
 */
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Employee() {
    }

    public Employee(String name, String surname, User user) {
        this.name = name;
        this.surname = surname;
        this.user = user;
    }
}
