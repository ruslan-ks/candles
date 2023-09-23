package r.kostiuk.candles.component.dto;

import r.kostiuk.candles.component.type.ComponentType;
import r.kostiuk.candles.component.vaildation.ValidComponent;

public class NewComponentRequest implements ValidComponent {
    private String name;
    private ComponentType type;

    public NewComponentRequest() {
    }

    public NewComponentRequest(String name, ComponentType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }
}
