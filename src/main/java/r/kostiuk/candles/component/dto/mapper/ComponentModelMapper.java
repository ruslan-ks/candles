package r.kostiuk.candles.component.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.component.dto.NewComponentRequest;

@org.springframework.stereotype.Component
@RequiredArgsConstructor
public class ComponentModelMapper implements ComponentMapper {
    private final ModelMapper modelMapper;

    @Override
    public Component toComponent(NewComponentRequest request) {
        return modelMapper.map(request, Component.class);
    }

    @Override
    public ComponentResponse toComponentResponse(Component component) {
        return new ComponentResponse(component.getId(), component.getName(), component.getType().getName());
    }
}
