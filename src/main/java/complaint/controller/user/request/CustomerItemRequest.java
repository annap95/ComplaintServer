package complaint.controller.user.request;

import complaint.controller.pagination.ItemRequest;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerItemRequest extends ItemRequest {

    private Long customerId;

    private String name;

    private String surname;

    private String streetName;

    private String streetNumber;

    private String postalCode;

    private String town;

    private String phone;

    private String email;

    private Boolean enabled;

    @Override
    public Map<String, String> getColumnNamesMap() {
        Map<String, String> result = new HashMap<>();
        result.put("email", "user.email");
        result.put("enabled", "user.enabled");
        return result;
    }
}
