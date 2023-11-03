package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.List;

public interface TestEntityFactory {

    /**
     * @return List of new Component instances. Every call on the same object produces unique instances
     */
    List<Component> createComponents(int count);

    /**
     * @return List of new Component instances. Every call on the same object  produces unique instances
     */
    List<ComponentType> createTypes(int count);
}
