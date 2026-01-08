package org.example.patientservice.controller;

import jakarta.validation.Valid;
import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.model.Patient;
import org.example.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<PatientResponseDTO>> getpatients(){
        List<PatientResponseDTO> patients =  patientService.getPatient();
        return ResponseEntity.ok().body(patients) ;
    }
    @PostMapping("/create")
    public ResponseEntity<PatientResponseDTO>createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createpatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO>updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatepatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

}
