package r.kostiuk.candles.component.dto;

import jakarta.validation.constraints.Min;
import lombok.*;
import r.kostiuk.candles.component.vaildation.ValidComponent;

@Data
public class NewComponentRequest implements ValidComponent {
    private String name;

    @Min(value = 1, message = "{component.typeId.min}")
    private long typeId;
}
