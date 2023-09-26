package in.obsoft.bizassist.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @GenericGenerator(name="custom_id", strategy = "in.obsoft.bizassist.base.utils.IdGenerator")
    @GeneratedValue(generator = "custom_id")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonIgnore
    private BranchEntity branchEntity;

    @OneToMany(mappedBy="brandEntity")
    private List<BrandImageEntity> brandImageEntities;

    @Transient
    private String branchId;

    @Transient
    private List<String> brandImagesInBase64;

    public BrandEntity(String id) {
        this.id = id;
    }

}
