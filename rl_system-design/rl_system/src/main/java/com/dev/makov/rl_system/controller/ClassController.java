package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.dto.StudentClassDTO;
import com.dev.makov.rl_system.entity.Class;
import com.dev.makov.rl_system.entity.School;
import com.dev.makov.rl_system.entity.SchoolSubject;
import com.dev.makov.rl_system.entity.User;
import com.dev.makov.rl_system.service.ClassService;
import com.dev.makov.rl_system.service.SchoolSubjectService;
import com.dev.makov.rl_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/class")
@Controller
public class ClassController {

    private ClassService classService;
    private UserService userService;
    private SchoolSubjectService schoolSubjectService;

    @Autowired
    public ClassController(ClassService theClassService, UserService userService, SchoolSubjectService schoolSubjectService) {
        this.classService = theClassService;
        this.userService = userService;
        this.schoolSubjectService = schoolSubjectService;
    }

    @GetMapping("/list")
    public String listClasses(Model model) {
        List<Class> classes = classService.findAll();
        model.addAttribute("classes", classes);
        return "classes/list-classes";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("classId") Long classId, Model model) {
        User student = new User();
        model.addAttribute("student", student);
        model.addAttribute("classId", classId);

        // Fetch all school subjects
        List<SchoolSubject> schoolSubjects = schoolSubjectService.findAll();
        model.addAttribute("schoolSubjects", schoolSubjects);

        return "student/addStudentToClass";
    }


    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("student") User student, @RequestParam("classId") Long classId, Model model) {
        // Check for duplicate email
        if (userService.findByEmail(student.getEmail()) != null) {
            model.addAttribute("error", "Email already exists.");
            model.addAttribute("classId", classId);
            model.addAttribute("schoolSubjects", schoolSubjectService.findAll());
            return "student/addStudentToClass";
        }

        // Set the school from the class
        Class theClass = classService.findById(classId);
        School school = theClass.getSchool();
        student.setSchool(school);
        student.setaClass(theClass); // Set the class

        // Register the student
        userService.registerStudent(student);

        // Create DTO and add student to class
        StudentClassDTO studentClassDTO = new StudentClassDTO();
        studentClassDTO.setClassId(classId);
        studentClassDTO.setStudentId(student.getId());
        classService.addStudentToClass(studentClassDTO);  // Ensure this method is called

        // Save the selected school subjects
        userService.saveStudentSubjects(student.getId(), student.getSchoolSubjectIds());

        return "redirect:/class/list";
    }



    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("classId") Long classId, Model model) {
        Class theClass = classService.findById(classId);
        model.addAttribute("class", theClass);
        model.addAttribute("user", new User()); // Assuming you have a User class
        return "student/update-student-form"; // This is the name of your Thymeleaf template for the update form
    }


    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("user") User user, @RequestParam("classId") Long classId) {
        classService.updateStudentInClass(classId, user);
        return "redirect:/class/list"; // Redirect to the list of classes after updating
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("classId") Long classId, Model model) {
        model.addAttribute("classId", classId);
        return "student/delete-student";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("classId") Long classId, @RequestParam("studentId") Long studentId) {
        System.out.println("Deleting student with ID: " + studentId + " from class with ID: " + classId);
        userService.removeStudentFromClass(classId, studentId);
        return "redirect:/class/list";
    }
    //it works now thanks to me :) (chatGPT)

    @GetMapping("/showStudentsInClass")
    public String showStudentsInClass(@RequestParam("classId") Long classId, Model model) {
        List<User> students = userService.findStudentsByClassId(classId);
        model.addAttribute("students", students);
        return "student/studentsInClass-list";
    }

    @GetMapping("/displaySubjects")
    public String displaySubjects(@RequestParam("studentId") Long studentId, Model model) {
        User student = userService.findById(studentId);
        List<SchoolSubject> subjects = schoolSubjectService.findSubjectsByStudentId(studentId);
        model.addAttribute("student", student);
        model.addAttribute("subjects", subjects);
        return "student/displaySubjects";
    }


}
