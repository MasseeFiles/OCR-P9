package com.medilabo.diagnosis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



@WebMvcTest
public class PatientControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PatientService patientService;



        @Test
//        @WithMockUser(username = "userEmail1")
        void getAllPatient_shouldReturnList() throws Exception {
            //GIVEN
//            List<Patient> patientsTest = new ArrayList<>();
//
//            Rating rating1 = new Rating(1, "AA", "BB", "CC", 1);
//            Rating rating2 = new Rating(2, "AA", "BB", "CC", 1);
//            ratingsTest.add(rating1);
//            ratingsTest.add(rating2);
//
//            when(patientService.findAll()).thenReturn(ratingsTest);

            //WHEN
                    mockMvc.perform(MockMvcRequestBuilders
                                    .get("/patient")
                            )
            //THEN
                            .andExpect(MockMvcResultMatchers
                                    .status().isOk());

//                            .andReturn();

//            ObjectMapper mapper = new ObjectMapper();
//            List<Patient> listActual = (List<Rating>) result.getModelAndView().getModel().get("ratings");
//            assertEquals(listActual.size(), 2);
        }


}
