package r.kostiuk.candles.component;

import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import r.kostiuk.candles.type.ComponentType_;

import java.util.List;

public class ComponentSpecifications {
    private ComponentSpecifications() {}

    public static Specification<Component> nameContainsIgnoreCase(String name) {
        String namePattern = '%' + name.toLowerCase() + '%';
        return (root, query, builder) -> {
            fetchType(root);
            return builder.like(builder.lower(root.get(Component_.name)), namePattern);
        };
    }

    public static Specification<Component> typeIdIn(List<Integer> typeIds) {
        return (root, query, builder) -> {
            fetchType(root);
            return root.join(Component_.type).get(ComponentType_.id).in(typeIds);
        };
    }

    private static void fetchType(Root<Component> root) {
        root.fetch(Component_.type);
    }
}
