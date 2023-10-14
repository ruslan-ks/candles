package r.kostiuk.candles.component.type.dto;

import lombok.Data;
import r.kostiuk.candles.component.type.validation.ValidComponentType;

@Data
public class NewComponentTypeRequest implements ValidComponentType {
    private String name;
    private String measurement;
}
