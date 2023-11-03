package r.kostiuk.candles.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import r.kostiuk.candles.type.dto.NewComponentTypeRequest;
import r.kostiuk.candles.type.validation.ComponentTypeControllerValidator;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentTypeControllerTest {

    @Mock
    private ComponentTypeService typeService;

    @Mock
    private ComponentTypeControllerValidator typeValidator;

    @InjectMocks
    private ComponentTypeController typeController;

    @Test
    void addMethodInvokesValidation() {
        var request = new NewComponentTypeRequest();
        typeController.add(request);
        verify(typeValidator).validate(request);
    }

    @Test
    void deleteMethodInvokesDeleteOnService() {
        int id = 1;
        typeController.delete(id);
        verify(typeService).deleteById(id);
    }
}