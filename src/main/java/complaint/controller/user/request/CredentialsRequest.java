<<<<<<< HEAD
package complaint.controller.user.request;

public class CredentialsRequest {
    private String email;
    private String password;

    public CredentialsRequest() {
    }

    public CredentialsRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
=======
package complaint.controller.user.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CredentialsRequest {

    @NotNull
    String email;

    @NotNull
    String password;

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
