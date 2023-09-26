package in.obsoft.bizassist.base.repositories;

import in.obsoft.bizassist.base.entities.BrandImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandImageRepository extends CrudRepository<BrandImageEntity, String> {

    List<BrandImageEntity> findByBrandEntityId(String brandId);

    @Query("delete from BrandImageEntity b where b.brandEntity.id=:brandId")
    void deleteImagesByBrandId(String brandId);

}
