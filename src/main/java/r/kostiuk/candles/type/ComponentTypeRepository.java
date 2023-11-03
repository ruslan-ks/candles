package r.kostiuk.candles.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import r.kostiuk.candles.type.dto.ComponentTypeResponse;

@Repository
public interface ComponentTypeRepository extends JpaRepository<ComponentType, Integer> {
    Page<ComponentTypeResponse> findBy(Pageable pageable);
    boolean existsByName(String componentTypeName);
}
