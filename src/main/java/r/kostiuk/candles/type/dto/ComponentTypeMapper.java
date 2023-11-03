package r.kostiuk.candles.type.dto;

import r.kostiuk.candles.type.ComponentType;

public interface ComponentTypeMapper {
    ComponentType toComponentType(NewComponentTypeRequest request);
    ComponentTypeResponse toComponentTypeResponse(ComponentType type);
}
