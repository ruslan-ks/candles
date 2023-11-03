package r.kostiuk.candles.type.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import r.kostiuk.candles.type.ComponentType;
import r.kostiuk.candles.type.ComponentTypeService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
abstract class ComponentTypeValidatorTestBase {
    protected ComponentTypeValidator validator;

    @Mock
    private ComponentTypeService typeService;

    @Mock
    private Errors errors;

    protected abstract ComponentTypeValidator getValidatorUnderTest();

    @BeforeEach
    void setUp() {
        validator = getValidatorUnderTest();
        validator.setTypeService(typeService);
    }

    @Test
    void existingTypeNameCausesValidationFail() {
        String existingTypeName = "Wax";
        when(typeService.exists(existingTypeName)).thenReturn(true);

        ValidComponentType type = new ComponentType(existingTypeName, "g");
        validator.validate(type, errors);

        verify(errors).rejectValue(eq("name"), eq("Unique"), any());
    }

    @Test
    void newTypeNamePassesValidation() {
        when(typeService.exists(anyString())).thenReturn(true);

        ValidComponentType type = new ComponentType("Wax", "g");
        validator.validate(type, errors);

        List<ObjectError> objectErrors = errors.getAllErrors();
        assertThat(objectErrors).isEmpty();
    }
}