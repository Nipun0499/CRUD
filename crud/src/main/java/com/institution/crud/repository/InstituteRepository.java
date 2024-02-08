package com.institution.crud.repository;

import com.institution.crud.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

    List<Institute> findAllByDeletedNot(boolean deleted);

    Optional<Institute> findByIdAndDeletedNot(Long id, boolean deleted);
}