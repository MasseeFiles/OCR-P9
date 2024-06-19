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

    // liste de tous les patients
    @GetMapping("")
    public List<Patient> getAllPatient() {

        logger.info("Requete pour obtenir la liste de tous les patients");

        return patientService.getAllPatient();    //retour d'un objet json implicite par utilisation de annotation @Restcontroller
    }

    // Data d'un patient particulier
    @GetMapping("/{id}")
    public Patient getSinglePatient(@PathVariable("id") Long patientId) {

        logger.info("Requete pour la consultation des informations d'un patient");

        return patientService.getSinglePatient(patientId);    //retour d'un objet json implicite par annotation @RestController
    }

    // Ajout d'un patient
    @PostMapping("")
    public void addPatient(@RequestBody Patient patientToAdd) {

        logger.info("Requete pour l'ajout d'un nouveau patient");

        patientService.add(patientToAdd);

//        if (result.hasErrors()) {
//        return "bidList/add";
//    } else {
//        bidListService.add(bidList);
//    }
//    return "redirect:/bidList/list";
    }

    // Update d'un patient
    @PutMapping("")
    public void updatePatient(@RequestBody Patient patientToUpdate) {

        logger.info("Requete pour la mise à jour des informations d'un patient");

        patientService.updatePatient(patientToUpdate);
    }

    // Suppression d'un patient
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") Long id) {

        logger.info("Requete pour la suppression d'un patient");

        patientService.deletePatient(id);
    }
}
