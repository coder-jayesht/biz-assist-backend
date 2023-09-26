package in.obsoft.bizassist.base.controllers;

import in.obsoft.bizassist.base.entities.BrandEntity;
import in.obsoft.bizassist.base.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<BrandEntity>> getBrandsByBranch(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortOn, @RequestParam String sortDirection, @RequestParam String branchId) {
        List<BrandEntity> brandEntities = brandService.getBrandsByBranch(pageNo, pageSize, sortOn, sortDirection, branchId);
        return ResponseEntity.ok(brandEntities);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BrandEntity> saveBrand(@RequestBody BrandEntity brandEntity) {
        BrandEntity updatedBrandEntity = brandService.saveBrand(brandEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBrandEntity);
    }

    @PutMapping
    public ResponseEntity<BrandEntity> updateBrand(@RequestBody BrandEntity brandEntity) {
        BrandEntity updatedBrandEntity = brandService.saveBrand(brandEntity);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBrandEntity);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
