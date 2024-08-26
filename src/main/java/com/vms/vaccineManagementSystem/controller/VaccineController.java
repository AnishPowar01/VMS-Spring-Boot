package com.vms.vaccineManagementSystem.controller;

import com.vms.vaccineManagementSystem.dao.VaccineDao;
import com.vms.vaccineManagementSystem.dto.UserDTO;
import com.vms.vaccineManagementSystem.dto.VaccineDoseDTO;
import com.vms.vaccineManagementSystem.entity.User;
import com.vms.vaccineManagementSystem.entity.VaccineDose;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    private VaccineDao vaccineDao;

    @GetMapping("/test")
    public String testServer()
    {
        return "server is up and running";
    }

    public VaccineController(VaccineDao vaccineDao)
    {
        this.vaccineDao = vaccineDao;
    }


    @GetMapping("/userDetails/{userId}")
    public UserDTO getUserDetails(@PathVariable Long userId)
    {
        return vaccineDao.getUserDetails(userId);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO)
    {
             UserDTO createdUser = vaccineDao.registerUser(userDTO);
             return createdUser;
    }

    @PostMapping("/addDose")
    public VaccineDoseDTO addVaccineDose(@RequestBody VaccineDoseDTO vaccineDoseDTO)
    {
        VaccineDoseDTO doseDTO = vaccineDao.addVaccineDose(vaccineDoseDTO);

        return doseDTO;
    }


}
