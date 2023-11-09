package r.kostiuk.candles.type.validation;

import jakarta.validation.constraints.NotEmpty;

public interface ValidComponentType {

    /**
     * Unique name
     */
    @NotEmpty
    String getName();

    @NotEmpty
    String getMeasurement();
}
