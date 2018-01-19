package complaint.controller.pagination;

import java.util.HashMap;
import java.util.Map;

public abstract class ItemRequest {

    public Map<String, String> getColumnNamesMap() {
        return new HashMap<>();
    }

}
