package in.obsoft.bizassist.base.services;

import in.obsoft.bizassist.base.entities.BillEntity;
import in.obsoft.bizassist.base.entities.BranchEntity;
import in.obsoft.bizassist.base.entities.BrandEntity;
import in.obsoft.bizassist.base.repositories.BillItemRepository;
import in.obsoft.bizassist.base.repositories.BillRepository;
import in.obsoft.bizassist.base.utils.AppFilterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillItemRepository billItemRepository;

    public List<BillEntity> getBrandsByBranch(int pageNo, int pageSize, String sortOn, String sortDirection, String branchId) {
        Pageable pageable = AppFilterUtil.getPageable(pageNo, pageSize, sortOn, sortDirection);
        List<BillEntity> billEntities = billRepository.findByBranchEntityId(branchId, pageable);
        return billEntities;
    }

    public BillEntity save(BillEntity billEntity) {
        billEntity.setBranchEntity(new BranchEntity(billEntity.getBranchId()));
        BillEntity updatedBillEntity = billRepository.save(billEntity);
        if(billEntity.getBillItems() != null && billEntity.getBillItems().size() > 0) {
            deleteBillItemByBillId(billEntity.getId());
            billEntity.getBillItems().forEach(item -> {
                item.setBrandEntity(new BrandEntity(item.getBrandId()));
                item.setBillEntity(billEntity);
            });
            billItemRepository.saveAll(billEntity.getBillItems());
        }
        return updatedBillEntity;
    }

    public void deleteBrand(String billId) {
        deleteBillItemByBillId(billId);
        billRepository.deleteById(billId);
    }

    public void deleteBillItemByBillId(String billId) {
        billItemRepository.deleteByBillEntityId(billId);
    }
}
