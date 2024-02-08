package com.institution.crud;


import com.institution.crud.dto.InstituteDto;
import com.institution.crud.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CrudApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class InstituteControllerIntegrationTest extends TestContainerAbstract{

    @Autowired
    RestTemplate restTemplate;

    @LocalServerPort
    String port;

    @Order(1)
    @Test
    void saveInstitute() {
        String url = "http://localhost:"+port+"/institute/add";
        InstituteDto instituteDto = new InstituteDto();
        instituteDto.setName("Institute 1");
        instituteDto.setLocation("Noida");
        instituteDto.setContactInfo("1234567890");
        instituteDto.setAdditionalDetails("NA");
        instituteDto.setDeleted(false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-REMOTE-USER-EMAIL", "er.nipun.jain.official@gmail.com");
        HttpEntity<InstituteDto> requestEntity = new HttpEntity<>(instituteDto,headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.POST
                ,requestEntity,
                Response.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Order(2)
    @Test
    void updateInstitute() {
        String url = "http://localhost:"+port+"/institute/1";
        InstituteDto instituteDto = new InstituteDto();
        instituteDto.setId(1L);
        instituteDto.setName("Updated Institute");
        instituteDto.setLocation("Noida");
        instituteDto.setContactInfo("1234567890");
        instituteDto.setAdditionalDetails("NA");
        instituteDto.setDeleted(false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-REMOTE-USER-EMAIL", "er.nipun.jain.official@gmail.com");
        HttpEntity<InstituteDto> requestEntity = new HttpEntity<>(instituteDto,headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.PUT
                ,requestEntity,
                Response.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Order(3)
    @Test
    void getAllInstitute() {
        String url = "http://localhost:"+port+"/institute/allInstitutes";
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.GET
                ,null,
                Response.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Order(4)
    @Test
    void getInstituteById() {
        String url = "http://localhost:"+port+"/institute/1";
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.GET
                ,null,
                Response.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Order(5)
    @Test
    void deleteInstituteById() {
        String url = "http://localhost:"+port+"/institute/1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-REMOTE-USER-EMAIL", "er.nipun.jain.official@gmail.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE
                ,requestEntity,
                Response.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
