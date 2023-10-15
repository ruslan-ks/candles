package r.kostiuk.candles.type.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import r.kostiuk.candles.type.ComponentTypeService;
import r.kostiuk.candles.validation.CustomValidator;

public abstract class ComponentTypeValidator extends CustomValidator<ValidComponentType> {
    private ComponentTypeService typeService;

    // Setter injection, so it's easier to mock when testing multiple implementations of this class
    @Autowired
    public void setTypeService(ComponentTypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(ValidComponentType.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var componentType = (ValidComponentType) target;
        if (typeService.exists(componentType.getName())) {
            errors.rejectValue("name", "Unique", "Type already exists");
        }
    }
}
