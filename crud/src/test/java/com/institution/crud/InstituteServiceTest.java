package com.institution.crud;

import com.institution.crud.dto.InstituteDto;
import com.institution.crud.entity.Institute;
import com.institution.crud.repository.InstituteRepository;
import com.institution.crud.response.Response;
import com.institution.crud.service.InstituteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InstituteServiceTest {

	@Mock
	private InstituteRepository instituteRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private InstituteService instituteService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveInstitute() {
		InstituteDto instituteDto = new InstituteDto();
		Institute institute = new Institute();
		when(modelMapper.map(instituteDto, Institute.class)).thenReturn(institute);
		when(instituteRepository.save(any(Institute.class))).thenReturn(institute);

		Response response = instituteService.saveInstitute(instituteDto);

		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getObject());
		verify(instituteRepository, times(1)).save(institute);
	}

	@Test
	public void testGetInstituteById_Success() {
		Long id = 1L;
		Institute institute = new Institute();
		institute.setId(id);
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.of(institute));
		when(modelMapper.map(institute, InstituteDto.class)).thenReturn(new InstituteDto());

		Response response = instituteService.getInstituteById(id);

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
	}

	@Test
	public void testGetInstituteById_NotFound() {
		Long id = 1L;
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.empty());

		Response response = instituteService.getInstituteById(id);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
	}

	@Test
	public void testGetAllInstitutes_NoInstitutesFound() {
		when(instituteRepository.findAllByDeletedNot(true)).thenReturn(Collections.emptyList());

		Response response = instituteService.getAllInstitutes();

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.getObject());
		assertTrue(((List<Institute>) response.getObject()).isEmpty());
		verify(instituteRepository, times(1)).findAllByDeletedNot(true);
	}

	@Test
	public void testDeleteInstituteById_Success() {
		Long id = 1L;
		Institute institute = new Institute();
		institute.setId(id);
		when(instituteRepository.findById(id)).thenReturn(Optional.of(institute));

		Response response = instituteService.deleteInstituteById(id);

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.getObject());
		verify(instituteRepository, times(1)).findById(id);
		verify(instituteRepository, times(1)).save(institute);
	}

	@Test
	public void testDeleteInstituteById_NotFound() {
		Long id = 1L;
		when(instituteRepository.findById(id)).thenReturn(Optional.empty());

		Response response = instituteService.deleteInstituteById(id);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findById(id);
		verify(instituteRepository, never()).save(any());
	}

	@Test
	public void testUpdateInstitute_Success() {
		Long id = 1L;
		InstituteDto instituteDto = new InstituteDto();
		Institute institute = new Institute();
		institute.setId(id);
		when(modelMapper.map(instituteDto, Institute.class)).thenReturn(institute);
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.of(institute));
		when(instituteRepository.save(any(Institute.class))).thenReturn(institute);

		Response response = instituteService.updateInstitute(id, instituteDto);

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
		verify(instituteRepository, times(1)).save(institute);
	}

	@Test
	public void testUpdateInstitute_NotFound() {
		Long id = 1L;
		InstituteDto instituteDto = new InstituteDto();
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.empty());

		Response response = instituteService.updateInstitute(id, instituteDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
		verify(instituteRepository, never()).save(any());
	}

	// Edge Cases
	@Test
	public void testSaveInstitute_NullDto() {
		Response response = instituteService.saveInstitute(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, never()).save(any());
	}

	@Test
	public void testGetInstituteById_NullId() {
		Response response = instituteService.getInstituteById(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, never()).findByIdAndDeletedNot(anyLong(), anyBoolean());
	}

	@Test
	public void testGetInstituteById_NonExistentId() {
		Long id = 999L;
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.empty());

		Response response = instituteService.getInstituteById(id);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
	}

	@Test
	public void testGetAllInstitutes_EmptyRepository() {
		when(instituteRepository.findAllByDeletedNot(true)).thenReturn(new ArrayList<>());

		Response response = instituteService.getAllInstitutes();

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.getObject());
		assertEquals(0, ((List<Institute>) response.getObject()).size());
		verify(instituteRepository, times(1)).findAllByDeletedNot(true);
	}

	@Test
	public void testDeleteInstituteById_NullId() {
		Response response = instituteService.deleteInstituteById(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, never()).findById(anyLong());
		verify(instituteRepository, never()).save(any());
	}

	@Test
	public void testDeleteInstituteById_NonExistentId() {
		Long id = 999L;
		when(instituteRepository.findById(id)).thenReturn(Optional.empty());

		Response response = instituteService.deleteInstituteById(id);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findById(id);
		verify(instituteRepository, never()).save(any());
	}

	@Test
	public void testUpdateInstitute_NullId() {
		Response response = instituteService.updateInstitute(null, new InstituteDto());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, never()).findByIdAndDeletedNot(anyLong(), anyBoolean());
		verify(instituteRepository, never()).save(any());
	}

	@Test
	public void testUpdateInstitute_NonExistentId() {
		Long id = 999L;
		when(instituteRepository.findByIdAndDeletedNot(id, true)).thenReturn(Optional.empty());

		Response response = instituteService.updateInstitute(id, new InstituteDto());

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertNull(response.getObject());
		verify(instituteRepository, times(1)).findByIdAndDeletedNot(id, true);
		verify(instituteRepository, never()).save(any());
	}

	// Positive Scenarios
	@Test
	public void testSaveInstitute_Positive() {
		InstituteDto instituteDto = new InstituteDto();
		Institute institute = new Institute();
		when(modelMapper.map(instituteDto, Institute.class)).thenReturn(institute);
		when(instituteRepository.save(any(Institute.class))).thenReturn(institute);

		Response response = instituteService.saveInstitute(instituteDto);

		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getObject());
		verify(instituteRepository, times(1)).save(institute);
	}

}