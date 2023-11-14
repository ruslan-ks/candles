package r.kostiuk.candles.component.dto.mapper;

import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.component.dto.NewComponentRequest;

public interface ComponentMapper {
    Component toComponent(NewComponentRequest request);
    ComponentResponse toComponentResponse(Component component);
}
