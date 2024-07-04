package com.medilabo.diagnosis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class PatientControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService patientService;

    @Test
//        @WithMockUser(username = "userEmail1")
    void getAllPatient_ShouldReturnJson() throws Exception {
        //GIVEN
        Patient[] patientArray = new Patient[4];
        patientArray[0] = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        patientArray[1] = new Patient(2L, "Test", "TestBorderline", LocalDate.of(1945, 6, 24), "M", "BB", "BB");
        patientArray[2] = new Patient(3L, "Test", "TestInDanger", LocalDate.of(2004, 6, 18), "M", "CC", "CC");
        patientArray[3] = new Patient(4L, "Test", "TestEarlyOnset", LocalDate.of(2002, 6, 28), "F", "DD", "DD");

        List<Patient> patientListTest = new ArrayList<>((Arrays.asList(patientArray)));

        when(patientService.getAllPatient()).thenReturn(patientListTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(jsonPath("$.size()").value(4));
    }

    @Test
//        @WithMockUser(username = "userEmail1")
    void getSinglePatient_ShouldReturnJson() throws Exception {
        //GIVEN
        Patient patientTest = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        when(patientService.getSinglePatient(1L)).thenReturn(patientTest);

        Long idTest = 1L;

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/{id}", idTest)
                )
                //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(jsonPath("$.patientId").value(patientTest.getPatientId()))
                .andExpect(jsonPath("$.firstName").value(patientTest.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patientTest.getLastName()));
    }

    @Test
    void updatePatient_ShouldUseMethodUpdatePatient() throws Exception {
        //GIVEN
        Patient patientToUpdateTest = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonString = mapper.writeValueAsString(patientToUpdateTest); //passage de patientToUpdateTest au format Json dans le Body, pas avec param

        Long idTest = 1L;

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/patient/{id}", idTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());

        //THEN
        Mockito.verify(patientService).updatePatient(patientToUpdateTest);
    }

    @Test
    void updatePatient_ShouldReturnException() throws Exception {
        //GIVEN
        Patient patientToUpdateTest = new Patient(4L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonString = mapper.writeValueAsString(patientToUpdateTest);

        Long idTest = 1L;

        //THEN
        assertThatThrownBy(() -> {
            mockMvc.perform(MockMvcRequestBuilders
                    .put("/patient/{id}", idTest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString));
        })
                .isInstanceOf(ServletException.class)
                .hasMessageContaining("Id passed in PathVariable ( " + idTest + " doesn't match Id passed in RequestBody (" + patientToUpdateTest.getPatientId());
    }
}
