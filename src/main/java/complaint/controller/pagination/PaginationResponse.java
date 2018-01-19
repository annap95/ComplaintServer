package complaint.controller.pagination;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T> {

    private long totalItems;

    private List<T> items;

}
