package r.kostiuk.candles.test.util;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.type.ComponentType;

import java.util.List;

public interface TestEntityFactory {

    /**
     * @return List of new Component instances
     * Every call on the same TestEntityFactory object produces unique instances
     */
    List<Component> createComponents(int count);

    /**
     * @return new Component instance
     * Every call on the same TestEntityFactory object produces unique instance
     */
    Component createComponent();

    /**
     * @return List of new Component instances with all fields initialized
     * Every call on the same TestEntityFactory object produces unique instances
     */
    List<ComponentType> createTypes(int count);

    /**
     * @return new ComponentType instance
     * Every call on the same TestEntityFactory object produces unique instance
     */
    ComponentType createType();
}
