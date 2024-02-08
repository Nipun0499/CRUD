package com.institution.crud.service;

import com.institution.crud.dto.InstituteDto;
import com.institution.crud.repository.InstituteRepository;
import com.institution.crud.entity.Institute;
import com.institution.crud.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InstituteService {
    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    ModelMapper modelMapper;

    public Response saveInstitute(InstituteDto instituteDto) {
        log.info("Inside saveInstitute of InstituteService");
        if(instituteDto==null)
            return new Response("Invalid data", HttpStatus.INTERNAL_SERVER_ERROR, null);
        Institute institute = modelMapper.map(instituteDto, Institute.class);
        log.info("Institute details saved!");
        return new Response("Institute details saved!", HttpStatus.CREATED,
                instituteRepository.save(institute));
    }

    public Response getInstituteById(Long id) {
        log.info("Inside getInstituteById of InstituteService");
        Optional<Institute> institute = instituteRepository.findByIdAndDeletedNot(id, true);
        return institute.map(value -> new Response("Institute fetched", HttpStatus.OK,
                        modelMapper.map(value, InstituteDto.class)))
                .orElseGet(() -> new Response("Institute details not found for mentioned Id",
                        HttpStatus.BAD_REQUEST, null));
    }

    public Response getAllInstitutes() {
        log.info("Inside getAllInstitutes of InstituteService");
        List<Institute> instituteList = instituteRepository.findAllByDeletedNot(true);
        if (instituteList.isEmpty())
            return new Response("No institutes found!", HttpStatus.OK, instituteList);
        else {
            log.info("Institute list of size {} fetched", instituteList.size());
            return new Response("Institutes list fetched!", HttpStatus.OK, instituteList);
        }
    }


    public Response deleteInstituteById(Long id) {
        log.info("Inside deleteInstituteById of InstituteService");
        Optional<Institute> instituteOptional = instituteRepository.findById(id);
        if (instituteOptional.isPresent()) {
            Institute institute = instituteOptional.get();
            institute.setDeleted(true);
            instituteRepository.save(institute);
            log.info("Institute with id {} deleted", id);
            return new Response("Institute deleted",
                    HttpStatus.OK, institute);
        } else {
            return new Response("Institute details not found for mentioned Id",
                    HttpStatus.BAD_REQUEST, null);
        }
    }

    public Response updateInstitute(Long id, InstituteDto instituteDto) {
        log.info("Inside updateInstitute of InstituteService");
        Optional<Institute> existingInstitute = instituteRepository.findByIdAndDeletedNot(id, true);
        if (existingInstitute.isPresent()) {
            Institute institute = modelMapper.map(instituteDto, Institute.class);
            institute.setId(id);
            instituteRepository.save(institute);
            log.info("Institute updated.");
            return new Response("Institute updated!", HttpStatus.OK, institute);
        } else {
            return new Response("Institute not found for update", HttpStatus.BAD_REQUEST, null);
        }
    }
}
