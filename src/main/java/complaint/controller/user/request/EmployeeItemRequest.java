package complaint.controller.user.request;

import complaint.controller.pagination.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeItemRequest extends ItemRequest {

    private Long employeeId;

    private String name;

    private String surname;

    private String userRole;

    private String email;

    private Boolean enabled;

    @Override
    public Map<String, String> getColumnNamesMap() {
        Map<String, String> result = new HashMap<>();
        result.put("userRole", "user.userRole");
        result.put("email", "user.email");
        result.put("enabled", "user.enabled");
        return result;
    }

}
