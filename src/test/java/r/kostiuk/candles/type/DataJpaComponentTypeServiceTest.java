package r.kostiuk.candles.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import r.kostiuk.candles.type.exception.ComponentTypeNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataJpaComponentTypeServiceTest {

    @Mock
    private ComponentTypeRepository typeRepository;

    @InjectMocks
    private DataJpaComponentTypeService typeService;

    @Test
    void findByIdThrowsIfNotFound() {
        int id = 1;
        when(typeRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> typeService.findById(id)).isInstanceOf(ComponentTypeNotFoundException.class);
    }

    @Test
    void deleteByIdCallsFindById() {
        typeService = spy(typeService);
        int id = 1;
        var typeOptional = Optional.of(new ComponentType());
        when(typeRepository.findById(id)).thenReturn(typeOptional);

        typeService.deleteById(id);

        verify(typeService).findById(id);
    }
}