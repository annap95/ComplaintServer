package complaint.controller.complaint.request;

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
public class ComplaintItemRequest extends ItemRequest {

    private Long complaintId;

    private String name;

    private String surname;

    private String status;

    private String submitDate;

    @Override
    public Map<String, String> getColumnNamesMap() {
        Map<String, String> result = new HashMap<>();
        result.put("name", "customer.name");
        result.put("surname", "customer.surname");
        return result;
    }

}
