package in.obsoft.bizassist.base.repositories;

import in.obsoft.bizassist.base.entities.BrandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<BrandEntity, String> {

    List<BrandEntity> findByBranchEntityId(String branchId, Pageable pageable);
}
