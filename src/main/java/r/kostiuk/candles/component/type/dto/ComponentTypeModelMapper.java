package r.kostiuk.candles.component.type.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import r.kostiuk.candles.component.dto.NewComponentRequest;
import r.kostiuk.candles.component.type.ComponentType;

@RequiredArgsConstructor
@Component
public class ComponentTypeModelMapper implements ComponentTypeMapper {
    private final ModelMapper modelMapper;

    @Override
    public ComponentType toComponentType(NewComponentRequest request) {
        return modelMapper.map(request, ComponentType.class);
    }

    @Override
    public ComponentTypeResponse toComponentTypeResponse(ComponentType type) {
        return new ComponentTypeResponse(type.getId(), type.getName(), type.getMeasurement());
    }
}
