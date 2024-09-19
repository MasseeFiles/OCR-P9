package com.medilabo.diagnosis_patients.service;

import com.medilabo.diagnosis_patients.model.Patient;
import com.medilabo.diagnosis_patients.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getSinglePatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found : Id used " + id));
    }

    public void updatePatient(Patient patient) {
        if (patientRepository.existsById(patient.getPatientId())) {
            patientRepository.save(patient);
        } else {
            throw new EntityNotFoundException("Patient to update not found : Id used " + patient.getPatientId());
        }
    }

}
