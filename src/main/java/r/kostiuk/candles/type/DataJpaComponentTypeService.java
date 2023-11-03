package r.kostiuk.candles.type;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import r.kostiuk.candles.type.dto.ComponentTypeCountResponse;
import r.kostiuk.candles.type.dto.ComponentTypeMapper;
import r.kostiuk.candles.type.dto.ComponentTypeResponse;
import r.kostiuk.candles.type.dto.NewComponentTypeRequest;
import r.kostiuk.candles.type.exception.ComponentTypeNotFoundException;

@RequiredArgsConstructor
@Service
public class DataJpaComponentTypeService implements ComponentTypeService {
    private final ComponentTypeRepository typeRepository;
    private final ComponentTypeMapper typeMapper;

    @Override
    public Page<ComponentTypeCountResponse> findPage(Pageable pageable) {
        return typeRepository.findComponentTypesCount(pageable);
    }

    @Override
    public ComponentTypeResponse add(NewComponentTypeRequest newComponentRequest) {
        ComponentType componentType = typeMapper.toComponentType(newComponentRequest);
        ComponentType savedComponentType = typeRepository.save(componentType);
        return typeMapper.toComponentTypeResponse(savedComponentType);
    }

    @Override
    public void deleteById(int id) throws ComponentTypeNotFoundException {
        ComponentType found = findById(id);
        typeRepository.delete(found);
    }

    @Override
    public ComponentType findById(int id) throws ComponentTypeNotFoundException {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ComponentTypeNotFoundException("ComponentType with id " + id + " not found"));
    }

    @Override
    public boolean exists(String componentTypeName) {
        return typeRepository.existsByName(componentTypeName);
    }
}
