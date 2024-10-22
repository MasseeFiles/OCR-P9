package com.medilabo.diagnosis_patients.controller;


import com.medilabo.diagnosis_patients.model.Patient;
import com.medilabo.diagnosis_patients.service.PatientService;
import jakarta.servlet.ServletException;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private static final Logger logger = LogManager.getLogger("PatientController");

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public List<Patient> getAllPatient() {

        logger.info("Requete pour obtenir la liste de tous les patients");

        List<Patient> patients = patientService.getAllPatient();
        return patients;
    }

    @GetMapping("/{id}")
    public Patient getSinglePatient(@PathVariable("id") Long patientId) {

        logger.info("Requete pour obtenir les données d'un patient particulier");

        return patientService.getSinglePatient(patientId);
    }

    @PutMapping("/{id}")
    public void updatePatient(
            @PathVariable("id") Long id,
            @RequestBody @Valid Patient patientToUpdate,
            BindingResult result
    ) throws Exception {

        logger.info("Requete pour la persistence des informations d'un patient");

        if (result.hasErrors()) {
            throw new ServletException("Request can't be handled, some patient data are missing or don't have the required format");
        } else {
            patientService.updatePatient(patientToUpdate);
        }
    }

}
