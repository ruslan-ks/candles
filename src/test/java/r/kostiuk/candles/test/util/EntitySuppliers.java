package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.function.Supplier;

public class EntitySuppliers {
    private EntitySuppliers() {}

    public static Supplier<Component> sequentialNameComponentSupplier(ComponentType type) {
        return new Supplier<>() {
            long index = 0;

            @Override
            public Component get() {
                var component = new Component();
                component.setName("Component " + ++index);
                type.addComponent(component);
                return component;
            }
        };
    }

    public static Supplier<ComponentType> sequentialNameTypeSupplier(String defaultMeasurement) {
        return new Supplier<>() {
            long index = 0;

            @Override
            public ComponentType get() {
                var type = new ComponentType();
                type.setName("Type " + ++index);
                type.setMeasurement(defaultMeasurement);
                return type;
            }
        };
    }
}
