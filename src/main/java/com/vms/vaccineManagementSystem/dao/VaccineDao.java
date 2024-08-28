package com.vms.vaccineManagementSystem.dao;

import com.vms.vaccineManagementSystem.controller.VaccineController;
import com.vms.vaccineManagementSystem.dto.UserDTO;
import com.vms.vaccineManagementSystem.dto.VaccineDoseDTO;
import com.vms.vaccineManagementSystem.entity.User;
import com.vms.vaccineManagementSystem.entity.VaccineDose;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VaccineDao {

    private EntityManager entityManager;

    public VaccineDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO)
    {
        User user = new User();
        user.setUserId(userDTO.getUserId()); // Assuming userId is provided
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setStatus("Not Vaccinated");

        Session session = entityManager.unwrap(Session.class);

        session.save(user);

        return mapToUserDTO(user, new ArrayList<>());
    }


    @Transactional
    public UserDTO getUserDetails(Long userId) {
//        String jpql = "SELECT vd FROM VaccineDose vd WHERE vd.user.userId = :userId";
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, userId);

        if(user == null) return null;

        User user1 =  updateVaccinationStatus(user);
        List<VaccineDoseDTO> vaccineDoseDTOs = user1.getVaccineDoses().stream().map(this::mapToVaccineDoseDTO).collect(Collectors.toList());
        System.out.println(vaccineDoseDTOs);
        System.out.println(user1);
        return mapToUserDTO(user1, vaccineDoseDTOs);
    }

    @Transactional
    public VaccineDose addVaccineDose(VaccineDoseDTO vaccineDoseDTO)
    {

        Long userId = vaccineDoseDTO.getUserId();
        System.out.println(vaccineDoseDTO);

        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, userId);
        int size = user.getVaccineDoses().size() - 1;
        VaccineDose vaccineDose = new VaccineDose();
        vaccineDose.setDoseNumber(vaccineDoseDTO.getDoseNumber());
        vaccineDose.setVaccinationDate(vaccineDoseDTO.getVaccinationDate());
        vaccineDose.setVaccineType(vaccineDoseDTO.getVaccineType());

        vaccineDose.setNextDueDate(vaccineDoseDTO.getVaccinationDate().plusDays(120));
        vaccineDose.setUser(user);

        System.out.println(vaccineDose);

        if(vaccineDoseDTO.getDoseNumber() == 1 && size < 0)
        {
            session.save(vaccineDose);
            User updatedUser = session.get(User.class, userId);
            User user1 =  updateVaccinationStatus(updatedUser);
        }
        else if(size >= 0 &&  user.getVaccineDoses().get(size).getDoseNumber() == vaccineDoseDTO.getDoseNumber()-1
                && vaccineDoseDTO.getVaccineType().equals(user.getVaccineDoses().get(size).getVaccineType()) && !vaccineDoseDTO.getVaccinationDate().isBefore(user.getVaccineDoses().get(size).getVaccinationDate().plusDays(120))

        && vaccineDoseDTO.getDoseNumber() < 4
        )
        {

            session.save(vaccineDose);

            User updatedUser = session.get(User.class, userId);
            User user1 =  updateVaccinationStatus(updatedUser);
            System.out.println(user1);
        }
        else
        {
            return null;
        }

        return vaccineDose;
    }

    @Transactional
    public List<User> getAllUserDetails() {
        Session session = entityManager.unwrap(Session.class);
        Query query=session.createQuery("from User",User.class);
        List<User> user=query.getResultList();
        return user;
    }


    private User updateVaccinationStatus(User user) {
        List<VaccineDose> doses = user.getVaccineDoses();

        int maxDoseCount = 0;

        for(VaccineDose vaccineDose : doses)
        {
            maxDoseCount = Math.max(vaccineDose.getDoseNumber(), maxDoseCount);
        }

        int doseCount = maxDoseCount;
        int doseHistory = doses.size();

        if (doseCount >= 2 && (doseHistory == 2 || doseHistory == 3)) {
            user.setStatus("Fully Vaccinated");
        } else if (doseCount == 1) {
            user.setStatus("Partially Vaccinated");
        } else {
            user.setStatus("Not Vaccinated");
        }

        return user;

    }

    @Transactional
    public String deleteUser(Long userId)
    {

        Session session = entityManager.unwrap(Session.class);

        User user = session.get(User.class, userId);

        if(user == null)
        {
            return null;
        }

        int size = user.getVaccineDoses().size() - 1;
        int count = user.getVaccineDoses().get(size).getDoseNumber();

        Optional<Integer> maxiDose = user.getVaccineDoses().stream().map(VaccineDose::getDoseNumber).max(Integer::compare);
        if(maxiDose.get() == 3)
        {
            session.delete(user);
        }
        else
        {
            return "Failure";
        }

        return "success";

    }


    private UserDTO mapToUserDTO(User user, List<VaccineDoseDTO> vaccineDoses) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setStatus(user.getStatus());
        userDTO.setVaccineDoses(vaccineDoses);
        return userDTO;
    }

    private VaccineDoseDTO mapToVaccineDoseDTO(VaccineDose vaccineDose) {
        VaccineDoseDTO dto = new VaccineDoseDTO();
        dto.setDoseId(vaccineDose.getDoseId());
        dto.setDoseNumber(vaccineDose.getDoseNumber());
        dto.setVaccinationDate(vaccineDose.getVaccinationDate());
        dto.setVaccineType(vaccineDose.getVaccineType());
        dto.setNextDueDate(vaccineDose.getNextDueDate());
        dto.setUserId(vaccineDose.getUser().getUserId());
        return dto;
    }
}
