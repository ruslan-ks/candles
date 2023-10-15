package r.kostiuk.candles.type.dto;

import lombok.Data;
import r.kostiuk.candles.type.validation.ValidComponentType;

@Data
public class NewComponentTypeRequest implements ValidComponentType {
    private String name;
    private String measurement;
}
