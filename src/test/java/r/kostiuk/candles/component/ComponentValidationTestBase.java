package r.kostiuk.candles.component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import r.kostiuk.candles.component.type.ComponentType;
import r.kostiuk.candles.component.vaildation.ValidComponent;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ComponentValidationTestBase {
    private static ValidatorFactory factory;
    private Validator validator;

    protected abstract ValidComponent getImplementationUnderTest();

    @BeforeAll
    static void beforeAll() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    static void afterAll() {
        factory.close();
    }

    @BeforeEach
    void setUp() {
        validator = factory.getValidator();
    }

    @Test
    void emptyNameNotAllowed() {
        ValidComponent componentWithEmptyName = getImplementationUnderTest();

        componentWithEmptyName.setName("");
        componentWithEmptyName.setType(new ComponentType("Type"));

        Set<ConstraintViolation<ValidComponent>> violations = validator.validate(componentWithEmptyName);
        assertThat(violations).isNotEmpty();
        System.out.println(violations);
    }
}
