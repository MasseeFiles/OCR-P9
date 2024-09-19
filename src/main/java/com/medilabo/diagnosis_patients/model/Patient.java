package com.medilabo.diagnosis_patients.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long patientId;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @NotNull
        private LocalDate dateOfBirth;

        @NotBlank
        private String gender;

        private String address;

        private String phoneNumber;

}
