package in.obsoft.bizassist.base.services;

import in.obsoft.bizassist.base.entities.BranchEntity;
import in.obsoft.bizassist.base.entities.BrandEntity;
import in.obsoft.bizassist.base.entities.BrandImageEntity;
import in.obsoft.bizassist.base.repositories.BrandImageRepository;
import in.obsoft.bizassist.base.repositories.BrandRepository;
import in.obsoft.bizassist.base.utils.AppFilterUtil;
import in.obsoft.bizassist.base.utils.AppUtils;
import in.obsoft.bizassist.base.utils.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandImageRepository brandImageRepository;

    @Autowired
    private CloudinaryUtils cloudinaryUtils;

    @Autowired
    private AppUtils appUtils;

    public List<BrandEntity> getBrandsByBranch(int pageNo, int pageSize, String sortOn, String sortDirection, String branchId) {
        Pageable pageable = AppFilterUtil.getPageable(pageNo, pageSize, sortOn, sortDirection);
        List<BrandEntity> brandEntities = brandRepository.findByBranchEntityId(branchId, pageable);
        return brandEntities;
    }

    public BrandEntity saveBrand(BrandEntity brandEntity) {
        brandEntity.setBranchEntity(new BranchEntity(brandEntity.getBranchId()));
        brandEntity.setBrandImageEntities(new ArrayList<>());
        List<BrandImageEntity> brandImageEntities = new ArrayList<>();
        brandEntity.getBrandImagesInBase64().forEach(image -> {
            try {
                Map resultMap = cloudinaryUtils.uploadImage(image);
                BrandImageEntity brandImageEntity = new BrandImageEntity();
                brandImageEntity.setPublicId((String) resultMap.get("public_id"));
                brandImageEntity.setSecureUrl((String) resultMap.get("secure_url"));
                brandImageEntity.setUrl((String) resultMap.get("url"));
                brandImageEntity.setBrandEntity(brandEntity);
                brandImageEntities.add(brandImageEntity);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        });
        BrandEntity updatedBrand = brandRepository.save(brandEntity);
        deleteBrandImages(brandImageRepository.findByBrandEntityId(updatedBrand.getId()));
        brandImageEntities.forEach(item -> item.setBrandEntity(updatedBrand));
        updatedBrand.setBrandImageEntities(brandImageEntities);
        brandImageRepository.saveAll(updatedBrand.getBrandImageEntities());
        updatedBrand.setBrandImagesInBase64(null);
        return updatedBrand;
    }

    public void deleteBrandImages(List<BrandImageEntity> brandImageEntities) {
        if(brandImageEntities != null) {
            brandImageEntities.forEach(item -> {
                try {
                    cloudinaryUtils.deleteImage(item.getPublicId());
                    brandImageRepository.deleteById(item.getPublicId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void deleteBrand(String brandId) {
        List<BrandImageEntity> imagesToDelete = brandImageRepository.findByBrandEntityId(brandId);
        deleteBrandImages(imagesToDelete);
        brandImageRepository.deleteAll(imagesToDelete);
        brandRepository.deleteById(brandId);
    }
}
