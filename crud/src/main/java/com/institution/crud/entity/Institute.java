package com.institution.crud.entity;

import com.institution.crud.config.AesGcmAttributeConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "institutes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Institute extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = AesGcmAttributeConverter.class)
    private String name;
    @Convert(converter = AesGcmAttributeConverter.class)
    private String location;
    @Convert(converter = AesGcmAttributeConverter.class)
    private String contactInfo;
    @Convert(converter = AesGcmAttributeConverter.class)
    private String additionalDetails;
    private boolean deleted;

}
