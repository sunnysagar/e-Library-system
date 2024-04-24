package com.sunny.Book.Library.System.controller;

import com.sunny.Book.Library.System.model.Admin;
import com.sunny.Book.Library.System.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint to server the signup page
    @GetMapping
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUpAdmin(@Valid @RequestBody Admin admin){
        adminService.registerAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin){
        Admin authenticated = adminService.validateTheAdminRegistration(admin);
        if(authenticated != null && adminService.authenticateUser(admin.getEmail(), admin.getPassword()))
        {
            return ResponseEntity.ok("Logged in successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }

    }
}
