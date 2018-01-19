package complaint.controller.pagination;

import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PaginationRequest<T extends ItemRequest> {

    @NotNull
    Integer pageSize;

    @NotNull
    Integer pageNumber;

    SortOptions sortOptions;

    T filterOptions;

    public Pageable mapToPageable(Class<T> c) {
        if(sortOptions == null || !getClassFields(c).contains(sortOptions.getColumnName()))
            return new PageRequest(pageNumber, pageSize);
        T t = getNewInstance(c);
        if(t.getColumnNamesMap().containsKey(sortOptions.getColumnName()))
            return new PageRequest(pageNumber, pageSize,
                    Sort.Direction.fromStringOrNull(sortOptions.getDirection()),
                    t.getColumnNamesMap().get(sortOptions.getColumnName()));
        return new PageRequest(pageNumber, pageSize,
                Sort.Direction.fromStringOrNull(sortOptions.getDirection()),
                sortOptions.getColumnName());
    }

    private List<String> getClassFields(Class<T> c) {
        return Arrays.stream(c.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
    }

    private T getNewInstance(Class<T> c) {
        try {
            return c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
