package r.kostiuk.candles.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long>, JpaSpecificationExecutor<Component> {
}
