package com.medilabo.diagnosis.model;

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
        //utiliser UI ID
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long patientId;         // pour les id, mettre plutot parametre en long plutot que integer (plafond max)

        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @NotNull(message = "Date of birth cannot be blank")
        private LocalDate dateOfBirth;

        @NotBlank(message = "Genre cannot be blank")
        private String genre;

        private String address;

        private String phoneNumber;
}
