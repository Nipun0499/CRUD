package com.institution.crud.controller;


import com.institution.crud.dto.InstituteDto;
import com.institution.crud.response.Response;
import com.institution.crud.service.InstituteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class InstituteController {
    @Autowired
    private InstituteService instituteService;

    @PostMapping("/add")
    public ResponseEntity<Response> registerInstitute(@Valid @RequestBody InstituteDto instituteDto) {
        Response response = instituteService.saveInstitute(instituteDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getInstituteById(@PathVariable Long id) {
        Response response = instituteService.getInstituteById(id);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @GetMapping("/allInstitutes")
    public ResponseEntity<Response> getAllInstitutes() {
        Response response = instituteService.getAllInstitutes();
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateInstitute(@PathVariable Long id, @Valid @RequestBody InstituteDto instituteDto) {
        Response response = instituteService.updateInstitute(id,instituteDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteInstitute(@PathVariable Long id) {
        Response response = instituteService.deleteInstituteById(id);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}