package r.kostiuk.candles.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import r.kostiuk.candles.dto.response.ComponentResponse;
import r.kostiuk.candles.entity.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    @Query("""
            SELECT new r.kostiuk.candles.dto.response.ComponentResponse(c.id, c.name, t.name)
            FROM Component c
            JOIN c.type t
            """)
    Page<ComponentResponse> findComponentsPage(Pageable pageable);
}
