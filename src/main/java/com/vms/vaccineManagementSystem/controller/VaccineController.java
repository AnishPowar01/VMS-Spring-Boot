package com.vms.vaccineManagementSystem.controller;

import com.vms.vaccineManagementSystem.dao.VaccineDao;
import com.vms.vaccineManagementSystem.dao.VaccineDaoImpl;
import com.vms.vaccineManagementSystem.dto.UserDTO;
import com.vms.vaccineManagementSystem.dto.VaccineDoseDTO;
import com.vms.vaccineManagementSystem.entity.User;
import com.vms.vaccineManagementSystem.entity.VaccineDose;
import com.vms.vaccineManagementSystem.exception.ConstraintsOverruledException;
import com.vms.vaccineManagementSystem.exception.DeleteConstraintsOverruledException;
import com.vms.vaccineManagementSystem.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Class VaccineController
 * @Description reponsible to handle all incoming requests regarding to vaccine registration
 */
@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    private VaccineDao vaccineDao;

    /**
     * @Method testServer
     * @Description for checking ping
     */
    @GetMapping("/test")
    public String testServer()
    {
        return "server is up and running";
    }

    public VaccineController(VaccineDaoImpl vaccineDaoImpl)
    {
        this.vaccineDao = vaccineDaoImpl;
    }


    /**
     * @Method getUserDetailsById
     * @Description responsible to fetch the details of particular user
     * @Params {Long userId}  Unique Identifier of user
     * @Return ResponseEntity<UserDTO>
     */

    @GetMapping("/userDetails/{userId}")
    public ResponseEntity<UserDTO> getUserDetailsById(@PathVariable Long userId) {
        UserDTO userDTO = vaccineDao.getUserDetailsById(userId);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    /**
     * @Method registerUser
     * @Description responsible to save the user in database
     * @Params {UserDto userDTO}  User details in term of UserDTO Object => refer UserDTO
     * @Return UserDTO
     */

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO)
    {
             UserDTO createdUser = vaccineDao.registerUser(userDTO);
             return createdUser;
    }

    /**
     * @Method addVaccineDose
     * @Description responsible to add vaccine dose to particular user
     * @Params {VaccineDoseDTO vaccineDoseDTO}  vaccination details in term of vaccineDoseDTO Object => refer VaccineDoseDTO
     * @Return ResponseEntity<VaccineDose>
     */

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

    /**
     * @Method deleteUser
     * @Description responsible to delete the particular user
     * @Params {Long userId}  Unique Identifier of user
     * @Return ResponseEntity<String>
     */

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

    /**
     * @Method getUserDetails
     * @Description responsible fetch the all user details
     * @Return List<User>
     */
    @GetMapping("/userDetails")
    public List<User> getUserDetails()
    {
        return vaccineDao.getAllUserDetails();
    }


}
