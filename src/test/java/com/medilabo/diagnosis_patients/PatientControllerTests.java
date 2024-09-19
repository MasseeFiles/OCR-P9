package com.medilabo.diagnosis_patients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.diagnosis_patients.model.Patient;
import com.medilabo.diagnosis_patients.service.PatientService;
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
    private PatientService mockPatientService;

    @Test
    void getAllPatient_ShouldReturnJson() throws Exception {
        //GIVEN
        Patient[] patientArray = new Patient[4];
        patientArray[0] = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        patientArray[1] = new Patient(2L, "Test", "TestBorderline", LocalDate.of(1945, 6, 24), "M", "BB", "BB");
        patientArray[2] = new Patient(3L, "Test", "TestInDanger", LocalDate.of(2004, 6, 18), "M", "CC", "CC");
        patientArray[3] = new Patient(4L, "Test", "TestEarlyOnset", LocalDate.of(2002, 6, 28), "F", "DD", "DD");

        List<Patient> patientListTest = new ArrayList<>((Arrays.asList(patientArray)));

        when(mockPatientService.getAllPatient()).thenReturn(patientListTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patientService")
                )
        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(jsonPath("$.size()").value(4));
    }

    @Test
    void getSinglePatient_ShouldReturnJson() throws Exception {
        //GIVEN
        Patient patientTest = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        when(mockPatientService.getSinglePatient(1L)).thenReturn(patientTest);

        Long idTest = 1L;

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patientService/{id}", idTest)
                )
        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(jsonPath("$.patientId").value(patientTest.getPatientId()))
                .andExpect(jsonPath("$.firstName").value(patientTest.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patientTest.getLastName()));
    }

    @Test
    void updatePatient_Ok() throws Exception {
        //GIVEN
        Patient patientToUpdateTest = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();    //utilisé pour mapper LocalDates
        String jsonString = mapper.writeValueAsString(patientToUpdateTest); //passage de patientToUpdateTest au format Json dans le Body, pas avec param

        Long idTest = 1L;

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/patientService/{id}", idTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk());

        Mockito.verify(mockPatientService).updatePatient(patientToUpdateTest);
    }

    @Test
    void updatePatient_DataValidationNotOk() throws Exception {
        //GIVEN
        Patient patientToUpdateTest = new Patient(1L, "newFirstName", "newLastName", LocalDate.of(1966, 12, 31), "M", "AA", "AA");
        patientToUpdateTest.setFirstName(null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonString = mapper.writeValueAsString(patientToUpdateTest);

        Long idTest = 1L;

        //THEN
        assertThatThrownBy(() -> {
            mockMvc.perform(MockMvcRequestBuilders
                    .put("/patientService/{id}", idTest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString));
        })
                .isInstanceOf(ServletException.class)
                .hasMessageContaining("Request can't be handled, some patient data are missing or don't have the required format");
    }

}
