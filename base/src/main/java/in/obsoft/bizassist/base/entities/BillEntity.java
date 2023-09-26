package in.obsoft.bizassist.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @GenericGenerator(name="custom_id", strategy = "in.obsoft.bizassist.base.utils.IdGenerator")
    @GeneratedValue(generator = "custom_id")
    @Column(name = "id")
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "discount")
    private double discount;

    @Column(name = "extra_discount")
    private double extraDiscount;

    @Column(name = "final_amount")
    private double finalAmount;

    @Column(name = "paid_type")
    private int paidType;

    @Column(name = "remaining_amount")
    private double remainingAmount;

    @Column(name = "total_bill")
    private double totalBill;

    @Column(name = "created_on", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp createdOn;

    @Column(name = "updated_on", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp updatedOn;

    @OneToMany(mappedBy="billEntity")
    private List<BillItem> billItems;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonIgnore
    private BranchEntity branchEntity;

    @Transient
    private String branchId;

    public BillEntity(String id) {
        this.id = id;
    }

}
