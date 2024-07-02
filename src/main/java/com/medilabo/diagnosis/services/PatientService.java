package com.medilabo.diagnosis.services;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.repositories.PatientRepository;
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
        return patientRepository.findById(id)   //findById() - methode automatiquement créee par Spring Boot
                .orElseThrow(() -> new IllegalArgumentException("Patient not found : Id used " + id));
    }

    public void add(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {
        //plus rapide que OrElseThrow car ne va pas chercher objet entier
        if (patientRepository.existsById(patient.getPatientId())) { // pas de method delete() car save() met à jour un objet (en base) qui à le meme id que l'objet transmis

        //control entre id passe dans requete http et id passé dans requestbody



            patientRepository.save(patient);
        } else {
            throw new EntityNotFoundException("Patient to update not found : Id used " + patient.getPatientId());
        }
    }

    public void deletePatient(Long id) { //m principe que update - verification par exist - voir aussi deletbyid()
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Patient to delete not found : Id used " + id);
        }
    }
}
