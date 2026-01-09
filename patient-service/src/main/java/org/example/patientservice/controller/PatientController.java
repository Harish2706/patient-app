package org.example.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.dto.validators.Createpatientvalidationgroup;
import org.example.patientservice.model.Patient;
import org.example.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient",description = "APi for managing Patient")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/get")
    @Operation(summary = "Get Patient")
    public ResponseEntity<List<PatientResponseDTO>> getpatients(){
        List<PatientResponseDTO> patients =  patientService.getPatient();
        return ResponseEntity.ok().body(patients) ;
    }
    @PostMapping("/create")
    @Operation(summary = "create patient")
    public ResponseEntity<PatientResponseDTO>createPatient(@Validated({Default.class, Createpatientvalidationgroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createpatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "update patient")
    public ResponseEntity<PatientResponseDTO>updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatepatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
     @DeleteMapping("/{id}")
     @Operation(summary = "delete patient")
    public ResponseEntity<Void>deletePatient(@PathVariable UUID id){
        patientService.deletepatient(id);
        return ResponseEntity.noContent().build();
    }
}
