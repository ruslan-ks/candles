package r.kostiuk.candles.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import r.kostiuk.candles.component.dto.ComponentResponse;

public interface ComponentService {
    Page<ComponentResponse> findComponentPage(Pageable pageable);
}
