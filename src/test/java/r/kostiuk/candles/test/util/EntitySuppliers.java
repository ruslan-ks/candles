package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.function.Supplier;

public class EntitySuppliers {
    private EntitySuppliers() {}

    public static Supplier<Component> sequentialNameComponentSupplier() {
        return new Supplier<>() {
            long index = 0;

            @Override
            public Component get() {
                var component = new Component();
                component.setName("Component " + ++index);
                return component;
            }
        };
    }

    public static Supplier<ComponentType> sequentialNameComponentTypeSupplier() {
        return new Supplier<>() {
            long index = 0;

            @Override
            public ComponentType get() {
                var type = new ComponentType();
                type.setName("Type " + ++index);
                return type;
            }
        };
    }
}
