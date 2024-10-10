package com.medilabo.diagnosis_patients.repository;

import com.medilabo.diagnosis_patients.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
