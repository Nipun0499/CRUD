package com.institution.crud.dto;


import lombok.Data;

@Data
public class InstituteDto {
    private Long id;
    private String name;
    private String location;
    private String contactInfo;
    private String additionalDetails;
    private boolean deleted;
}
