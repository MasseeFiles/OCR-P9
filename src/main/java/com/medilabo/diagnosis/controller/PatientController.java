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

    //html : fetch get
    @GetMapping
    public List<Patient> getAllPatient() {

        logger.info("Requete pour obtenir la liste de tous les patients");

        List<Patient> patients = patientService.getAllPatient();
        return patients;
    }

    // Update d'un patient - persistence
    //html : fetch post
    //mettre validation not null pour tt sauf address et phone number

    //objet doit etre dans body
    //ajouter id en pathvariable
    @PutMapping("")//{id}
    public void updatePatient (@RequestBody Patient patientToUpdate){

        logger.info("Requete pour la mise à jour des informations d'un patient");

        patientService.updatePatient(patientToUpdate);
    }

//    public ModelAndView showPatientList(ModelAndView modelAndView) {
//
//        logger.info("Requete pour obtenir la page testFetch complète");
//
//         modelAndView.setViewName("/patient/index");
//         return modelAndView;

    // Ajout d'un patient - affichage formulaire
    //comment juste renvoyer une vue - pareil pour endpoint update patient form : renvoi string vue OU Objet patient
//    @GetMapping("/add")
//    public String showPatientAddForm()  {
//
//        logger.info("Requete pour l'affichage du formulaire d'ajout d'un patient");
//
//        //retourner vue du formulaire add patient
//        return "patient/add";
//    }

    // Ajout d'un patient - persistence
//    @PostMapping("")
//    public void addPatient(@RequestBody Patient patientToAdd) {
//
//        logger.info("Requete pour l'ajout d'un nouveau patient");
//
//        patientService.add(patientToAdd);

//synthaxe avec validation sur parametres d'entree
//        if (result.hasErrors()) {
//        return "bidList/add";
//    } else {
//        bidListService.add(bidList);
//    }
//    return "redirect:/bidList/list";
//}

// Update d'un patient - affichage form
//comment juste renvoyer une vue - pareil pour endpoint update patient form
//    @GetMapping("/{id}")
//    public Patient showPatientUpdateForm(@PathVariable("id") Long id) {
//
//        logger.info("Requete pour l'affichage du formulaire d'update d'un patient");
//
//        Patient patientToShow = patientService.getSinglePatient(id);
//        return patientToShow;
//    }


}
