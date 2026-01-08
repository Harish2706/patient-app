package org.example.patientservice.service;

import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.exception.EmailAlreadyExistsException;
import org.example.patientservice.exception.PatientNotFoundException;
import org.example.patientservice.mapper.PatientMapper;
import org.example.patientservice.model.Patient;
import org.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;

     public PatientService(PatientRepository patientRepository){
         this.patientRepository = patientRepository;
     }

     public List<PatientResponseDTO> getPatient(){
         List<Patient> patients = patientRepository.findAll();
         List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(PatientMapper::toDTO).toList();
         return patientResponseDTOS;
     }
     public PatientResponseDTO createpatient(PatientRequestDTO patientRequestDTO){
         if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
             throw new EmailAlreadyExistsException("Patient of the email already exits" + patientRequestDTO.getEmail());
         }
         Patient patient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));
         return PatientMapper.toDTO(patient);
     }

     public PatientResponseDTO updatepatient(UUID id, PatientRequestDTO patientRequestDTO){
         Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient with id: " + id + " not found"));
         patient.setName(patientRequestDTO.getName());
          patient.setAddress(patientRequestDTO.getAddress());
          patient.setEmail(patientRequestDTO.getEmail());
          patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
          Patient updatedPatient = patientRepository.save(patient);
          return PatientMapper.toDTO(updatedPatient);

     }
}
