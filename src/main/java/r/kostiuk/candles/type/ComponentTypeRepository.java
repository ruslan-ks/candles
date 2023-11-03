package r.kostiuk.candles.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import r.kostiuk.candles.type.dto.ComponentTypeCountResponse;

@Repository
public interface ComponentTypeRepository extends JpaRepository<ComponentType, Integer> {

    @Query("""
            select new r.kostiuk.candles.type.dto.ComponentTypeCountResponse(ct, count(c))
            from ComponentType ct
            left join ct.components c
            group by ct
            """)
    Page<ComponentTypeCountResponse> findComponentTypesCount(Pageable pageable);

    boolean existsByName(String componentTypeName);
}
