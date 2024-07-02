package com.medilabo.diagnosis.controller;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient") //defines a base URI for all the endpoints within the controller.
public class PatientController {
    private static final Logger logger = LogManager.getLogger("PatientController");
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatient() {

        logger.info("Requete pour obtenir la liste de tous les patients");

        List<Patient> patients = patientService.getAllPatient();
        return patients;
    }

    //mettre validation not null pour tt sauf address et phone number

    //objet doit etre dans body
    //ajouter id en pathvariable
    @PutMapping("")//{id}
    public void updatePatient (@RequestBody Patient patientToUpdate){

        logger.info("Requete pour la mise à jour des informations d'un patient");

        patientService.updatePatient(patientToUpdate);
    }
}
