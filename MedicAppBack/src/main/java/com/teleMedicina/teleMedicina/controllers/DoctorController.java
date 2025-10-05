package com.teleMedicina.teleMedicina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teleMedicina.teleMedicina.services.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/especialidad/{especialidad}")
    public List<DoctorDto> getDoctorsBySpecialty(@PathVariable String especialidad) {
        return doctorService.getDoctorsBySpecialty(especialidad);
    }
}
