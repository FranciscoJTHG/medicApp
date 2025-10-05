package com.teleMedicina.teleMedicina.models.medicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repository interface for managing Doctor entities.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    UserDetails findById(Long id);
}
