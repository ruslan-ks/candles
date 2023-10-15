package r.kostiuk.candles.type.validation;

import jakarta.validation.constraints.NotEmpty;

public interface ValidComponentType {
    @NotEmpty
    String getName();

    @NotEmpty
    String getMeasurement();
}
