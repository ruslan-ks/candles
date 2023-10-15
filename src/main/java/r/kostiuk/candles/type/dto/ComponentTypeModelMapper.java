package r.kostiuk.candles.type.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import r.kostiuk.candles.type.ComponentType;

@RequiredArgsConstructor
@Component
public class ComponentTypeModelMapper implements ComponentTypeMapper {
    private final ModelMapper modelMapper;

    @Override
    public ComponentType toComponentType(NewComponentTypeRequest request) {
        return modelMapper.map(request, ComponentType.class);
    }

    @Override
    public ComponentTypeResponse toComponentTypeResponse(ComponentType type) {
        return new ComponentTypeResponse(type.getId(), type.getName(), type.getMeasurement());
    }
}
