package com.vms.vaccineManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccine_dose")
public class VaccineDose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOSE_ID")
    private Long doseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonIgnore
    private User user;

//    @Positive(message = "Dose number must be a positive integer")
    @Column(name = "DOSE_NUMBER", nullable = false)
    private int doseNumber;

//    @NotNull(message = "Vaccination date is mandatory")
    @Column(name = "VACCINATION_DATE", nullable = false)
    private LocalDate vaccinationDate;

//    @NotBlank(message = "Vaccine type is mandatory")
//    @Size(max = 50, message = "Vaccine type must be less than 50 characters")
    @Column(name = "VACCINE_TYPE", nullable = false, length = 50)
    private String vaccineType;

    @Transient
    private LocalDate nextDueDate;

    public VaccineDose(){}

    public Long getDoseId() {
        return doseId;
    }

    public void setDoseId(Long doseId) {
        this.doseId = doseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    @Override
    public String toString() {
        return "VaccineDose{" +
                "doseId=" + doseId +
                ", doseNumber=" + doseNumber +
                ", vaccinationDate=" + vaccinationDate +
                ", vaccineType='" + vaccineType + '\'' +
                ", nextDueDate=" + nextDueDate +
                '}';
    }

}
