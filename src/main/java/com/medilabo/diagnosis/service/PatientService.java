package com.medilabo.diagnosis.service;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.repository.PatientRepository;
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
        //plus rapide que OrElseThrow car ne va pas chercher objet entier
        // pas de method delete() car save() met à jour un objet (en base) qui à le meme id que l'objet transmis
        if (patientRepository.existsById(patient.getPatientId())) {
            patientRepository.save(patient);
        } else {
            throw new EntityNotFoundException("Patient to update not found : Id used " + patient.getPatientId());
        }
    }
}
