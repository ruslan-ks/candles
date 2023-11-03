package r.kostiuk.candles.type.exception;

import r.kostiuk.candles.exception.ItemNotFoundException;

public class ComponentTypeNotFoundException extends ItemNotFoundException {
    public ComponentTypeNotFoundException(String message) {
        super(message);
    }
}
