package com.vms.vaccineManagementSystem.dao;

import com.vms.vaccineManagementSystem.dto.UserDTO;
import com.vms.vaccineManagementSystem.dto.VaccineDoseDTO;
import com.vms.vaccineManagementSystem.entity.User;
import com.vms.vaccineManagementSystem.entity.VaccineDose;

import java.util.List;

public interface VaccineDao {

    UserDTO registerUser(UserDTO userDTO);

    UserDTO getUserDetailsById(Long userId);

    VaccineDose addVaccineDose(VaccineDoseDTO vaccineDoseDTO);

    List<User> getAllUserDetails();

    String deleteUser(Long userId);
}

