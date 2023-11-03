package r.kostiuk.candles.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import r.kostiuk.candles.type.dto.ComponentTypeResponse;
import r.kostiuk.candles.type.dto.NewComponentTypeRequest;
import r.kostiuk.candles.type.exception.ComponentTypeNotFoundException;

public interface ComponentTypeService {
    Page<ComponentTypeResponse> findPage(Pageable pageable);
    ComponentTypeResponse create(NewComponentTypeRequest newComponentRequest);
    void deleteById(int componentTypeId) throws ComponentTypeNotFoundException;
    ComponentType findById(int componentTypeId) throws ComponentTypeNotFoundException;
    boolean exists(String componentTypeName);
}
