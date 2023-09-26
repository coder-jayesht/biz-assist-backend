package in.obsoft.bizassist.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandImageEntity {

    @Id
    @Column(name = "public_id")
    private String publicId;

    @Column(name = "secure_url")
    private String secureUrl;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonIgnore
    private BrandEntity brandEntity;

}
