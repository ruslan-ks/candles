package r.kostiuk.candles.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import r.kostiuk.candles.test.util.PostgresContainerExtension;
import r.kostiuk.candles.test.util.TestEntityFactory;
import r.kostiuk.candles.test.util.TestEntityFactoryImpl;
import r.kostiuk.candles.type.dto.ComponentTypeResponse;
import r.kostiuk.candles.type.dto.NewComponentTypeRequest;
import r.kostiuk.candles.type.exception.ComponentTypeNotFoundException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(PostgresContainerExtension.class)
class ComponentTypeServiceIntegrationTest {

    @Autowired
    private ComponentTypeService typeService;

    private final TestEntityFactory entityFactory = new TestEntityFactoryImpl();

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void findDeletedTypeByIdThrows() {
        NewComponentTypeRequest typeDto = createNewComponentTypeRequest();

        ComponentTypeResponse type = typeService.add(typeDto);
        typeService.deleteById(type.id());

        int id = type.id();
        assertThatThrownBy(() -> typeService.findById(id)).isInstanceOf(ComponentTypeNotFoundException.class);

    }

    @Test
    void addAndFindById() {
        NewComponentTypeRequest typeDto = createNewComponentTypeRequest();

        ComponentTypeResponse addedType = typeService.add(typeDto);
        ComponentType foundType = typeService.findById(addedType.id());

        assertEqual(foundType, addedType);
    }

    private static void assertEqual(ComponentType actual, ComponentTypeResponse expected) {
        assertThat(actual.getId()).isEqualTo(expected.id());
        assertThat(actual.getName()).isEqualTo(expected.name());
        assertThat(actual.getMeasurement()).isEqualTo(expected.measurement());
    }

    private NewComponentTypeRequest createNewComponentTypeRequest() {
        ComponentType type = entityFactory.createTypes(1).get(0);
        return toNewComponentTypeRequest(type);
    }

    private NewComponentTypeRequest toNewComponentTypeRequest(ComponentType type) {
        return modelMapper.map(type, NewComponentTypeRequest.class);
    }
}
