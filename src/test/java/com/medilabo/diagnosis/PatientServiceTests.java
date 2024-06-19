package com.medilabo.diagnosis;

import com.medilabo.diagnosis.model.Patient;
import com.medilabo.diagnosis.services.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This test class uses a H2 Database
 * and is initialized by H2DataInitializer bean
 * @see H2DataInitializer
 */
@SpringBootTest
public class PatientServiceTests {
    @Autowired
    private PatientService patientService;
//    @Mock
//    private PatientRepository patientRepository;
//
//    @BeforeEach
//    public void setup() {
//        patientRepository = mock(PatientRepository.class);
//    }

//    @Test
//    void getSinglePatient_Ok() {
//        //GIVEN
//        Patient patientExpected = new Patient();
//        patientExpected.setPatientId(1L);
//        patientExpected.setFirstName("Test");
//        patientExpected.setLastName("TestNone");
//        patientExpected.setGenre("F");
//        patientExpected.setAddress("AA");
//        patientExpected.setPhoneNumber("AA");
//        patientExpected.setDateOfBirth(LocalDate.of(1966,12,31));
//
////        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientExpected));
//
//        //WHEN
//        Patient patientActual = patientService.getSinglePatient(1L);
//
//        //THEN
//        assertThat(patientExpected).usingRecursiveComparison()
//                .isEqualTo(patientActual);
//    }

    @Test
    void getSinglePatient_Not_In_DB() {
        //GIVEN
        Long patientIdNotInDb = 10L;

        //WHEN

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
        Patient patientExpected = new Patient();
        patientExpected.setPatientId(1L);
        patientExpected.setFirstName("newFirstName");
        patientExpected.setLastName("newLastName");
        patientExpected.setGenre("F");
        patientExpected.setDateOfBirth(LocalDate.of(1966, 12, 31));

        //WHEN
        patientService.updatePatient(patientExpected);

        //THEN
        Patient patientActual = patientService.getSinglePatient(1L);
        assertThat(patientExpected).usingRecursiveComparison()
                .isEqualTo(patientActual);
    }

    @Test
    void updatePatientData_Patient_Not_Found() {
        //GIVEN
        Patient patientNotInDB = new Patient();
        patientNotInDB.setPatientId(10L);    //id not in H2 DB
        patientNotInDB.setFirstName("First");
        patientNotInDB.setLastName("Last");
        patientNotInDB.setGenre("F");
        patientNotInDB.setDateOfBirth(LocalDate.of(1966, 12, 31));

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            patientService.updatePatient(patientNotInDB);
        })
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Patient to update not found : Id used 10");
    }

//    @Test
//    void deletePatient_Ok() {
//        //GIVEN
//        Patient patientNotInDB = new Patient();
//        patientNotInDB.setPatientId(10L);    //id not in test Db
//        patientNotInDB.setFirstName("First");
//        patientNotInDB.setLastName("Last");
//        patientNotInDB.setGenre("F");
//        patientNotInDB.setDateOfBirth(LocalDate.of(1966,12,31));
//
//        //WHEN
//
//        //THEN
//        assertThatThrownBy(() -> {
//            patientService.updatePatient(patientNotInDB);
//        })
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessageContaining("Patient to update not found : Id used 10");
//    }

    @Test
    void deletePatient_Not_In_DB() {
        //GIVEN
        Patient patientNotInDB = new Patient();
        patientNotInDB.setPatientId(10L);    //id not in test Db
        patientNotInDB.setFirstName("First");
        patientNotInDB.setLastName("Last");
        patientNotInDB.setGenre("F");
        patientNotInDB.setDateOfBirth(LocalDate.of(1966, 12, 31));

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            patientService.deletePatient(patientNotInDB.getPatientId());
        })
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Patient to delete not found : Id used 10");
    }
}
