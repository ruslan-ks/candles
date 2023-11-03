package r.kostiuk.candles.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.test.util.TestEntityFactory;
import r.kostiuk.candles.test.util.TestEntityFactoryImpl;
import r.kostiuk.candles.test.util.PostgresContainerExtension;
import r.kostiuk.candles.type.dto.ComponentTypeCountResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ExtendWith(PostgresContainerExtension.class)
class ComponentTypeRepositoryTest {
    private final TestEntityFactory entityFactory = new TestEntityFactoryImpl();

    @Autowired
    private ComponentTypeRepository typeRepository;

    @Test
    void findComponentTypesCountForOneType() {
        ComponentType type = entityFactory.createTypes(1).get(0);
        addComponents(type, 2);
        typeRepository.save(type);

        Pageable pageable = PageRequest.of(0, 1);
        List<ComponentTypeCountResponse> actual = typeRepository.findComponentTypesCount(pageable).getContent();

        List<ComponentTypeCountResponse> expected = toComponentTypeCountResponses(List.of(type));
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void findComponentTypesCountForMultipleTypes() {
        int typeCount = 3;
        List<ComponentType> types = entityFactory.createTypes(typeCount);
        addComponents(types.get(0), 2);
        addComponents(types.get(1), 3);
        typeRepository.saveAll(types);

        Pageable pageable = PageRequest.of(0, typeCount);
        List<ComponentTypeCountResponse> actual = typeRepository.findComponentTypesCount(pageable).getContent();

        List<ComponentTypeCountResponse> expected = toComponentTypeCountResponses(types);
        assertThat(actual).hasSameElementsAs(expected);
    }

    private void addComponents(ComponentType type, int count) {
        List<Component> components = entityFactory.createComponents(count);
        components.forEach(type::addComponent);
    }

    private List<ComponentTypeCountResponse> toComponentTypeCountResponses(List<ComponentType> types) {
        return types.stream()
                .map(this::toComponentTypeCountResponse)
                .toList();
    }

    private ComponentTypeCountResponse toComponentTypeCountResponse(ComponentType type) {
        return new ComponentTypeCountResponse(type, type.getComponents().size());
    }
}