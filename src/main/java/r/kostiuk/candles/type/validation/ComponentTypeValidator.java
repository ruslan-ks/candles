package r.kostiuk.candles.type.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import r.kostiuk.candles.messages.MessageProvider;
import r.kostiuk.candles.type.ComponentTypeService;
import r.kostiuk.candles.validation.CustomValidator;

@RequiredArgsConstructor
public abstract class ComponentTypeValidator extends CustomValidator<ValidComponentType> {
    private static final String UNIQUE_ERROR_CODE = "Unique";

    private final MessageProvider messageProvider;
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
            rejectWithUniqueError(errors);
        }
    }

    private void rejectWithUniqueError(Errors errors) {
        String message = messageProvider.getDefaultMessage(UNIQUE_ERROR_CODE);
        errors.rejectValue("name", UNIQUE_ERROR_CODE, message);
    }
}
