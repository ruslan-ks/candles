package r.kostiuk.candles.component;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import r.kostiuk.candles.component.dto.ComponentResponse;

@RequiredArgsConstructor
@Service
public class DataJpaComponentService implements ComponentService {
    private final ComponentRepository componentRepository;

    @Override
    public Page<ComponentResponse> findComponentPage(Pageable pageable) {
        return componentRepository.findComponentsPage(pageable);
    }
}
