package r.kostiuk.candles.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import r.kostiuk.candles.dto.response.ComponentResponse;

public interface ComponentService {
    Page<ComponentResponse> findComponentPage(Pageable pageable);
}
