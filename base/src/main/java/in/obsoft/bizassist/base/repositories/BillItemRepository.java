package in.obsoft.bizassist.base.repositories;

import in.obsoft.bizassist.base.entities.BillItem;
import org.springframework.data.repository.CrudRepository;

public interface BillItemRepository extends CrudRepository<BillItem, String> {

    void deleteByBillEntityId(String billId);

}
