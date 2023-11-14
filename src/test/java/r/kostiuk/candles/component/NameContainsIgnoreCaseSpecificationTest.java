package r.kostiuk.candles.component;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NameContainsIgnoreCaseSpecificationTest extends AbstractComponentSpecificationTest {
    @Test
    void findOneByNameContains() {
        String orange = "Orange";
        List<String> namesToFind = List.of("Apple", "Peach", "Pear", "Plum", orange, "Banana");
        Map<String, Component> nameComponentMap = createComponentsWithNamesContaining(namesToFind);
        componentRepository.saveAll(nameComponentMap.values());

        Specification<Component> spec = ComponentSpecifications.nameContainsIgnoreCase(orange);

        List<Component> expected = List.of(nameComponentMap.get(orange));
        List<Component> actual = componentRepository.findAll(spec);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void findOneByNameContainsIgnoreCase() {
        String orange = "Orange";
        List<String> namesToFind = List.of("Apple", "Peach", "Pear", "Plum", orange, "Banana");
        Map<String, Component> nameComponentMap = createComponentsWithNamesContaining(namesToFind);
        componentRepository.saveAll(nameComponentMap.values());

        String upperNameToFind = orange.toUpperCase();
        Specification<Component> spec = ComponentSpecifications.nameContainsIgnoreCase(upperNameToFind);

        List<Component> expected = List.of(nameComponentMap.get(orange));
        List<Component> actual = componentRepository.findAll(spec);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Map<String, Component> createComponentsWithNamesContaining(List<String> namesToFind) {
        return namesToFind.stream()
                .collect(Collectors.toMap(Function.identity(), this::createComponentWithNameContaining));
    }

    private Component createComponentWithNameContaining(String keyword) {
        Component component = entityFactory.createComponent();
        component.setName(component.getName() + " " + keyword + " ...some useless text");
        return component;
    }

    @Test
    void findMultipleByNameContainsIgnoreCase() {
        List<Component> components = Stream.of("Apple", "Peach", "Pear", "Plum", "Orange", "Banana")
                .map(this::createComponentWithName)
                .toList();
        componentRepository.saveAll(components);

        String letterToFind = "p";
        Specification<Component> spec = ComponentSpecifications.nameContainsIgnoreCase(letterToFind);

        List<Component> expected = components.stream()
                .filter(component -> nameContainsIgnoreCase(component, letterToFind))
                .toList();
        List<Component> actual = componentRepository.findAll(spec);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Component createComponentWithName(String name) {
        var component = entityFactory.createComponent();
        component.setName(name);
        return component;
    }

    private boolean nameContainsIgnoreCase(Component component, String substring) {
        return component.getName().toLowerCase().contains(substring.toLowerCase());
    }
}
