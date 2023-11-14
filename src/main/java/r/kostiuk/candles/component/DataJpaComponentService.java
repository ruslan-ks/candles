package r.kostiuk.candles.component;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.kostiuk.candles.component.dto.ComponentFilterRequest;
import r.kostiuk.candles.component.dto.mapper.ComponentMapper;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.component.dto.NewComponentRequest;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DataJpaComponentService implements ComponentService {
    private final ComponentRepository componentRepository;
    private final ComponentMapper componentMapper;

    @Transactional
    @Override
    public ComponentResponse add(NewComponentRequest request) {
        Component component = componentMapper.toComponent(request);
        component = componentRepository.save(component);
        return componentMapper.toComponentResponse(component);
    }

    @Override
    public Page<ComponentResponse> findPage(ComponentFilterRequest filter, Pageable pageable) {
        Specification<Component> spec = buildSpecification(filter);
        Page<Component> page = componentRepository.findAll(spec, pageable);
        return page.map(componentMapper::toComponentResponse);
    }

    private static Specification<Component> buildSpecification(ComponentFilterRequest filter) {
        Specification<Component> spec = Specification.where(null);
        
        String name = filter.getName();
        if (name != null && !name.isBlank()) {
            spec = spec.and(ComponentSpecifications.nameContainsIgnoreCase(name));
        }

        List<Integer> typeIds = filter.getTypeIds();
        if (typeIds != null && !typeIds.isEmpty()) {
            spec = spec.and(ComponentSpecifications.typeIdIn(typeIds));
        }

        return spec;
    }
}
