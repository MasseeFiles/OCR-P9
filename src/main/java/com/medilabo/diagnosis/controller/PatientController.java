package com.medilabo.diagnosis.controller;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient") //defines a base URI for all the endpoints within the controller.
@Tag(name = "DiagnosisController", description = "Gestion des patients")
public class PatientController {
    private static final Logger logger = LogManager.getLogger("PatientController");
    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Recuperation des données de tous les patients", description = "Retourne une List<Patient>")
    public List<Patient> getAllPatient() {

        logger.info("Requete pour obtenir la liste de tous les patients");

        List<Patient> patients = patientService.getAllPatient();
        return patients;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperation des données d'un patient particulier", description = "Retourne un objet Patient")
    public Patient getSinglePatient(@PathVariable("id") Long patientId) {

        logger.info("Requete pour obtenir les données d'un patient particulier");

        return patientService.getSinglePatient(patientId);
    }

    //mettre validation not null pour tt sauf address et phone number

    @PutMapping("/{id}")
    @Operation(summary = "Mise à jour des données d'un patient", description = "Persiste en base les données actualisées d'un patient")
    public void updatePatient(@PathVariable("id") Long id, @RequestBody Patient patientToUpdate) throws Exception {

        logger.info("Requete pour la mise à jour des informations d'un patient");

        if (id.equals(patientToUpdate.getPatientId())) {
            patientService.updatePatient(patientToUpdate);
        } else {
            throw new ServletException("Id passed in PathVariable ( " + id + " doesn't match Id passed in RequestBody (" + patientToUpdate.getPatientId());
        }
    }
}
