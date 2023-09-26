package in.obsoft.bizassist.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bill_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {

    @Id
    @GenericGenerator(name="custom_id", strategy = "in.obsoft.bizassist.base.utils.IdGenerator")
    @GeneratedValue(generator = "custom_id")
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonIgnore
    private BillEntity billEntity;

    @OneToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private BrandEntity brandEntity;

    @Column(name = "total_brand_count")
    private double totalBrandCount;

    @Column(name = "total_amount")
    private double totalAmount;

    @Transient
    private String brandId;

    public BillItem(String id) {
        this.id = id;
    }

}
