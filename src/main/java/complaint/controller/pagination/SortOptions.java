package complaint.controller.pagination;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class SortOptions {

    @NotNull
    String columnName;

    String direction;
}
