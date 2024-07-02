package com.medilabo.diagnosis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@WebMvcTest
public class PatientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;


    @Test
//        @WithMockUser(username = "userEmail1")
    void getAllPatient_ShouldReturnList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk());
    }


}
