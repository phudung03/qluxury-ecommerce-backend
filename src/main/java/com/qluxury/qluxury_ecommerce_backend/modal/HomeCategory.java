package com.qluxury.qluxury_ecommerce_backend.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qluxury.qluxury_ecommerce_backend.domain.HomeCategorySection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HomeCategory {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;
    private String image;
    private String categoryId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private HomeCategorySection section;
}
