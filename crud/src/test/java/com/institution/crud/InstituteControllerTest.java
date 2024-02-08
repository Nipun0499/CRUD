package com.institution.crud;

import com.institution.crud.controller.InstituteController;
import com.institution.crud.dto.InstituteDto;
import com.institution.crud.response.Response;
import com.institution.crud.service.InstituteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InstituteControllerTest {

    @Mock
    private InstituteService instituteService;

    @InjectMocks
    private InstituteController instituteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterInstitute_Success() throws Exception {
        InstituteDto instituteDto = new InstituteDto();
        when(instituteService.saveInstitute(any(InstituteDto.class)))
                .thenReturn(new Response("Institute registered successfully", HttpStatus.CREATED, null));

        ResponseEntity<Response> responseEntity = instituteController.registerInstitute(instituteDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetInstituteById_Success() throws Exception {
        Long id = 1L;
        when(instituteService.getInstituteById(id))
                .thenReturn(new Response("Institute fetched successfully", HttpStatus.OK, null));

        ResponseEntity<Response> responseEntity = instituteController.getInstituteById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllInstitutes_Success() throws Exception {
        when(instituteService.getAllInstitutes())
                .thenReturn(new Response("All institutes fetched successfully", HttpStatus.OK, null));

        ResponseEntity<Response> responseEntity = instituteController.getAllInstitutes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateInstitute_Success() throws Exception {
        Long id = 1L;
        InstituteDto instituteDto = new InstituteDto();
        when(instituteService.updateInstitute(id, instituteDto))
                .thenReturn(new Response("Institute updated successfully", HttpStatus.OK, null));

        ResponseEntity<Response> responseEntity = instituteController.updateInstitute(id, instituteDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteInstitute_Success() throws Exception {
        Long id = 1L;
        when(instituteService.deleteInstituteById(id))
                .thenReturn(new Response("Institute deleted successfully", HttpStatus.OK, null));

        ResponseEntity<Response> responseEntity = instituteController.deleteInstitute(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

