package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestEntityFactoryImpl implements TestEntityFactory {
    private static final String DEFAULT_TYPE_MEASUREMENT = "g";

    private final Supplier<Component> componentSupplier = EntitySuppliers.sequentialNameComponentSupplier();
    private final Supplier<ComponentType> typeSupplier = EntitySuppliers.sequentialNameComponentTypeSupplier();

    @Override
    public List<Component> createComponents(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(i -> componentSupplier.get())
                .toList();
    }

    @Override
    public List<ComponentType> createTypes(int count) {
        return Stream.iterate(1, i -> ++i)
                .limit(count)
                .map(this::createType)
                .toList();
    }

    private ComponentType createType(int typeNumber) {
        var type = typeSupplier.get();
        type.setMeasurement(DEFAULT_TYPE_MEASUREMENT);
        return type;
    }
}
