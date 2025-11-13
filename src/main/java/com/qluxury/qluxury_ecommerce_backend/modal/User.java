package com.qluxury.qluxury_ecommerce_backend.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // khi gửi dữ liệu đi thì có thể ghi vào password, nhưng khi trả về thì không bao giờ trả về password
    private String password;

    private String email;
    private String fullName;
    private String mobile;
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses = new HashSet<>(); // Sử dụng Set để tránh trùng lặp địa chỉ

    @ManyToMany
    @JsonIgnore // bỏ qua và không gán cho thuộc tính này khi serializing đối tượng User thành JSON
    private Set<Coupon> usedCoupons = new HashSet<>();
}
