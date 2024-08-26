package com.vms.vaccineManagementSystem.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vms.vaccineManagementSystem.entity.User;

import java.time.LocalDate;

public class VaccineDoseDTO {

    private Long doseId;
    private int doseNumber;
    private LocalDate vaccinationDate;
    private String vaccineType;
    private LocalDate nextDueDate;
    @JsonIgnore
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDoseId() {
        return doseId;
    }

    public void setDoseId(Long doseId) {
        this.doseId = doseId;
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
}
