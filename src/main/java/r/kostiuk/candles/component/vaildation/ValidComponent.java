package r.kostiuk.candles.component.vaildation;

import jakarta.validation.constraints.NotEmpty;

public interface ValidComponent {
    @NotEmpty(message = "{component.name.notEmpty}")
    String getName();
}
