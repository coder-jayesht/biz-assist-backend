package in.obsoft.bizassist.base.repositories;

import in.obsoft.bizassist.base.entities.BillEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<BillEntity, String> {

    List<BillEntity> findByBranchEntityId(String branchId, Pageable pageable);

}
