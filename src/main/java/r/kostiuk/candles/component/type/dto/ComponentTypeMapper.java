package r.kostiuk.candles.component.type.dto;

import r.kostiuk.candles.component.type.ComponentType;

public interface ComponentTypeMapper {
    ComponentType toComponentType(NewComponentTypeRequest request);
    ComponentTypeResponse toComponentTypeResponse(ComponentType type);
}
