package r.kostiuk.candles.component;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import r.kostiuk.candles.type.ComponentType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TypeIdInSpecificationTest extends AbstractComponentSpecificationTest {

    @Test
    void findByTypeOneTypeId() {
        List<Component> components = entityFactory.createComponents(10);
        ComponentType typeToFind = entityFactory.createType();

        List<Component> componentsWithTypeToFind = components.subList(0, 5);
        componentsWithTypeToFind.forEach(typeToFind::addComponent);
        componentRepository.saveAll(components);

        Specification<Component> spec = ComponentSpecifications.typeIdIn(List.of(typeToFind.getId()));

        List<Component> actual = componentRepository.findAll(spec);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(componentsWithTypeToFind);
    }

    @Test
    void findByMultipleTypeIds() {
        Map<ComponentType, List<Component>> typeComponentsMap = createTypeComponentsMap(2, 3);
        typeComponentsMap.values().forEach(componentRepository::saveAll);
        createAndSaveComponents(10); // default type components

        List<Integer> typeIds = toTypeIds(typeComponentsMap.keySet());
        Specification<Component> spec = ComponentSpecifications.typeIdIn(typeIds);

        List<Component> expected = typeComponentsMap.values()
                .stream()
                .flatMap(List::stream)
                .toList();
        List<Component> actual = componentRepository.findAll(spec);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Map<ComponentType, List<Component>> createTypeComponentsMap(int typeCount, int componentPerType) {
        return entityFactory.createTypes(typeCount)
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        type -> createComponentsOfType(type, componentPerType)
                ));
    }

    private void createAndSaveComponents(int count) {
        List<Component> components = entityFactory.createComponents(count);
        componentRepository.saveAll(components);
    }

    private List<Component> createComponentsOfType(ComponentType type, int count) {
        List<Component> components = entityFactory.createComponents(3);
        components.forEach(type::addComponent);
        return components;
    }

    private List<Integer> toTypeIds(Collection<ComponentType> types) {
        return types.stream()
                .map(ComponentType::getId)
                .toList();
    }
}