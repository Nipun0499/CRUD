//package com.institution.crud;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.institution.crud.dto.InstituteDto;
//import com.institution.crud.entity.Institute;
//import com.institution.crud.repository.InstituteRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//@AutoConfigureMockMvc
//@Testcontainers
//@TestPropertySource(properties = {
//        "spring.datasource.url=jdbc:tc:postgresql://localhost/test",
//        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
//        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"
//})
//public class InstituteControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private InstituteRepository instituteRepository;
//
//    @Container
//    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("test")
//            .withUsername("test")
//            .withPassword("test");
//
//    @BeforeEach
//    public void setup() {
//        Institute institute = new Institute();
//        institute.setId(1L);
//        institute.setName("Test Institute");
//
//        when(instituteRepository.findById(1L)).thenReturn(java.util.Optional.of(institute));
//        when(instituteRepository.findAllByDeletedNot(true)).thenReturn(Collections.singletonList(institute));
//    }
//
//    @Test
//    public void testGetInstituteById_Success() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"message\":\"Institute fetched successfully\",\"status\":\"OK\",\"object\":{\"id\":1,\"name\":\"Test Institute\"}}"));
//    }
//
//    @Test
//    public void testGetInstituteById_NotFound() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/2"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json("{\"message\":\"Institute details not found for mentioned Id\",\"status\":\"BAD_REQUEST\",\"object\":null}"));
//    }
//
//    @Test
//    public void testRegisterInstitute_Success() throws Exception {
//        InstituteDto instituteDto = new InstituteDto();
//        instituteDto.setName("Test Institute");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(instituteDto)))
//                .andExpect(status().isCreated())
//                .andExpect(content().json("{\"message\":\"Institute details saved!\",\"status\":\"CREATED\",\"object\":{\"id\":1,\"name\":\"Test Institute\"}}"));
//    }
//
//    @Test
//    public void testGetAllInstitutes_Success() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/allInstitutes"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"message\":\"All institutes fetched successfully\",\"status\":\"OK\",\"object\":[{\"id\":1,\"name\":\"Test Institute\"}]}"));
//    }
//
//    @Test
//    public void testUpdateInstitute_Success() throws Exception {
//        InstituteDto instituteDto = new InstituteDto();
//        instituteDto.setName("Updated Institute");
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(instituteDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"message\":\"Institute updated successfully\",\"status\":\"OK\",\"object\":{\"id\":1,\"name\":\"Updated Institute\"}}"));
//    }
//
//    @Test
//    public void testDeleteInstitute_Success() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"message\":\"Institute deleted successfully\",\"status\":\"OK\",\"object\":null}"));
//    }
//}
