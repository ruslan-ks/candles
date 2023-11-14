package r.kostiuk.candles.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.component.dto.ComponentFilterRequest;
import r.kostiuk.candles.component.dto.NewComponentRequest;

public interface ComponentService {

    ComponentResponse add(NewComponentRequest request);

    /**
     * Find page by specified filtering parameters
     * @param filter filter criteria dto; if any of fields is empty or null, it is ignored.
     */
    Page<ComponentResponse> findPage(ComponentFilterRequest filter, Pageable pageable);
}
