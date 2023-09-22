package r.kostiuk.candles.component.vaildation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import r.kostiuk.candles.component.type.ComponentType;

public interface ValidComponent {
    @NotEmpty
    @NotNull
    String getName();

    void setName(String name);

    @NotNull
    ComponentType getType();

    void setType(ComponentType type);
}
