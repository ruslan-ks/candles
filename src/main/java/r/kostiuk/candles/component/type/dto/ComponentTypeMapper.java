package r.kostiuk.candles.component.type.dto;

import r.kostiuk.candles.component.dto.NewComponentRequest;
import r.kostiuk.candles.component.type.ComponentType;

public interface ComponentTypeMapper {
    ComponentType toComponentType(NewComponentRequest request);
    ComponentTypeResponse toComponentTypeResponse(ComponentType type);
}
