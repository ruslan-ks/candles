package r.kostiuk.candles.component.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ComponentFilterRequest {
    private String name;
    private List<Integer> typeIds = Collections.emptyList();
}
