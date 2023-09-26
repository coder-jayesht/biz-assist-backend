package in.obsoft.bizassist.base.controllers;

import in.obsoft.bizassist.base.entities.BillEntity;
import in.obsoft.bizassist.base.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<BillEntity>> getBillsByBranch(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortOn, @RequestParam String sortDirection, @RequestParam String branchId) {
        List<BillEntity> billEntities = billService.getBrandsByBranch(pageNo, pageSize, sortOn, sortDirection, branchId);
        return ResponseEntity.ok(billEntities);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BillEntity> save(@RequestBody BillEntity billEntity) {
        BillEntity updatedBillEntity = billService.save(billEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBillEntity);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<BillEntity> update(@RequestBody BillEntity billEntity) {
        BillEntity updatedBillEntity = billService.save(billEntity);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBillEntity);
    }

    @DeleteMapping("/{billId}")
    @Transactional
    public ResponseEntity<Void> deleteBrand(@PathVariable String billId) {
        billService.deleteBrand(billId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
