package com.vms.vaccineManagementSystem.controller;

import com.vms.vaccineManagementSystem.dao.VaccineDao;
import com.vms.vaccineManagementSystem.dto.UserDTO;
import com.vms.vaccineManagementSystem.dto.VaccineDoseDTO;
import com.vms.vaccineManagementSystem.entity.User;
import com.vms.vaccineManagementSystem.entity.VaccineDose;
import com.vms.vaccineManagementSystem.exception.ConstraintsOverruledException;
import com.vms.vaccineManagementSystem.exception.DeleteConstraintsOverruledException;
import com.vms.vaccineManagementSystem.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        UserDTO userDTO = vaccineDao.getUserDetails(userId);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }


    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO)
    {
             UserDTO createdUser = vaccineDao.registerUser(userDTO);
             return createdUser;
    }

    @PostMapping("/addDose")
    public ResponseEntity<VaccineDose> addVaccineDose(@RequestBody VaccineDoseDTO vaccineDoseDTO)
    {
        VaccineDose vaccineDose = vaccineDao.addVaccineDose(vaccineDoseDTO);

        if(vaccineDose != null)
        {
            return  ResponseEntity.ok(vaccineDose);
        }
        else {
            throw new ConstraintsOverruledException("Exception Occured due to presence of one of the scenario :" +
                    "1) check if previous dose are present before adding 2nd or 3rd dose" +
                    "2) vaccine Type Should be same" +
                    "3)  A minimum of 120 days must pass between doses");
        }


    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
        String response =  vaccineDao.deleteUser(userId);

        if(response == null)
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        else if(response.equals("Failure")) {

            throw new DeleteConstraintsOverruledException("User should have minimum 3 doses");
        }
        else
        {
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/userDetails")
    public List<User> getUserDetails()
    {
        return vaccineDao.getAllUserDetails();
    }


}
