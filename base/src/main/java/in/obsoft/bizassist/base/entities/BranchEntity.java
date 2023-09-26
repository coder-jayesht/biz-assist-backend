package in.obsoft.bizassist.base.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "addressLine")
    private String addressLine;

    @ManyToMany
    @JoinTable(
            name = "user_branch",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> userEntities;

    @OneToMany(mappedBy="branchEntity")
    private List<BrandEntity> brandEntities;

    @OneToMany(mappedBy="branchEntity")
    private List<BillEntity> billEntities;

    public BranchEntity(String id) {
        this.id = id;
    }

}
