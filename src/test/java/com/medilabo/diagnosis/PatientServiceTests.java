package com.medilabo.diagnosis;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.repositories.PatientRepository;
import com.medilabo.diagnosis.services.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This test class uses a H2 Database
 * and is initialized by H2DataInitializer bean
 * @see H2DataInitializer
 */
@SpringBootTest
public class PatientServiceTests {
    @Autowired
    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    public void setup() {
        patientRepository = mock(PatientRepository.class);
    }

    @Test
    void getSinglePatient_Ok() {
        //GIVEN
        Patient patientExpected = new Patient(1L, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "AA", "AA");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientExpected));

        //WHEN
        Patient patientActual = patientService.getSinglePatient(1L);

        //THEN
        assertThat(patientExpected).usingRecursiveComparison()
                .isEqualTo(patientActual);
    }

    @Test
    void getSinglePatient_Not_In_DB() {
        //GIVEN
        Long patientIdNotInDb = 10L;

        //THEN
        assertThatThrownBy(() -> {
            patientService.getSinglePatient(patientIdNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Patient not found : Id used 10");
    }

    @Test
    void updatePatient_Ok() {
        //GIVEN
        Patient patientExpected = new Patient(1L, "newFirstName", "newLastName", LocalDate.of(1966, 12, 31), "M", "AA", "AA");

        //WHEN
        patientService.updatePatient(patientExpected);

        //THEN
        Patient patientActual = patientService.getSinglePatient(1L);
        assertThat(patientExpected).usingRecursiveComparison()
                .isEqualTo(patientActual);
    }

    @Test
    void updatePatient_ID_Not_Found() {
        //GIVEN
        Patient patientNotInDB = new Patient(12L, "newFirstName", "newLastName", LocalDate.of(1966, 12, 31), "M", "AA", "AA");

        //THEN
        assertThatThrownBy(() -> {
            patientService.updatePatient(patientNotInDB);
        })
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Patient to update not found : Id used 12");
    }
}


