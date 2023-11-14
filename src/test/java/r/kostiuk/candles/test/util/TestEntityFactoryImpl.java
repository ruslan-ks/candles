package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestEntityFactoryImpl implements TestEntityFactory {
    private static final String DEFAULT_TYPE_MEASUREMENT = "g";

    private final Supplier<ComponentType> typeSupplier =
            EntitySuppliers.sequentialNameTypeSupplier(DEFAULT_TYPE_MEASUREMENT);

    private final Supplier<Component> componentSupplier =
            EntitySuppliers.sequentialNameComponentSupplier(createType());

    @Override
    public List<Component> createComponents(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(i -> createComponent())
                .toList();
    }

    @Override
    public Component createComponent() {
        return componentSupplier.get();
    }

    @Override
    public List<ComponentType> createTypes(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(i -> this.createType())
                .toList();
    }

    @Override
    public ComponentType createType() {
        return typeSupplier.get();
    }
}
