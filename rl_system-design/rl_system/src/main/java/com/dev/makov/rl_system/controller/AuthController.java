package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.entity.School;
import com.dev.makov.rl_system.entity.User;
import com.dev.makov.rl_system.service.SchoolService;
import com.dev.makov.rl_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private UserService userService;
    private SchoolService schoolService;

    @Autowired
    public AuthController(UserService userService, SchoolService schoolService) {
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String showRegisterForms() {
        return "reg";
    }

    @GetMapping("/register/student")
    public String registerStudent(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("schools", schoolService.findAll()); // Populate with the list of schools
        return "student/register-student";
    }

    @PostMapping("/register/student")
    public String registerStudentUser(@ModelAttribute("user") User user, @RequestParam("school.id") Long schoolId) {
        School school = schoolService.findById(schoolId);
        user.setSchool(school);
        userService.registerStudent(user);
        return "redirect:/login";
    }

    @GetMapping("/register/teacher")
    public String registerTeacher(Model model) {
        model.addAttribute("user", new User());
        return "teacher/register-teacher";
    }


    @GetMapping("/register/parent")
    public String registerParent(Model model) {
        model.addAttribute("user", new User());
        return "parent/register-parent";
    }

    @PostMapping("/register/parent")
    public String registerParentUser(@ModelAttribute("user") User user) {
        userService.registerParent(user);
        return "redirect:/login";
    }
}
