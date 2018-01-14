package complaint.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import complaint.model.user.enums.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @NotNull
    @Column(name = "enabled")
    private boolean enabled;

    public boolean isEmployee() {
        return this.userRole == UserRole.ADMIN || this.userRole == UserRole.CONSULTANT;
    }
}
