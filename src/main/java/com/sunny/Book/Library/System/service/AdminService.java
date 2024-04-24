package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.expection.DuplicateUserException;
import com.sunny.Book.Library.System.model.Admin;
import com.sunny.Book.Library.System.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public void registerAdmin(Admin admin) {

        if(adminRepository.findByEmail(admin.getEmail()).isPresent()){
            throw new DuplicateUserException("Duplicate User");
        }

        Admin newadmin = new Admin();
        newadmin.setName(admin.getName());
        newadmin.setEmail(admin.getEmail());
        newadmin.setPhone(admin.getPhone());
        newadmin.setPassword(admin.getPassword());

        adminRepository.save(newadmin);

    }

    public boolean authenticateUser(String email, String password) {
        // Retrieve the user from the database using the username
        Optional<Admin> user = adminRepository.findByEmail(email);

        // Check if the user exists and if the provided password matches the stored password
        Admin correctUser = user.get();
        return correctUser.getPassword().equals(password);
    }

    public Admin validateTheAdminRegistration(Admin admin) {
        return adminRepository.findByEmailAndPassword(admin.getEmail(), admin.getPassword()).orElse(null);


    }
}
