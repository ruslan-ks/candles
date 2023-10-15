package r.kostiuk.candles.type.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import r.kostiuk.candles.type.ComponentTypeService;
import r.kostiuk.candles.validation.CustomValidator;

@RequiredArgsConstructor
public abstract class ComponentTypeValidator extends CustomValidator<ValidComponentType> {
    private final ComponentTypeService typeService;

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
