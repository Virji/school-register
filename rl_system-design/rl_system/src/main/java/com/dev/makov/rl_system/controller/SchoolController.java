package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.dao.GradeRepository;
import com.dev.makov.rl_system.dao.SchoolSubjectRepository;
import com.dev.makov.rl_system.entity.*;
import com.dev.makov.rl_system.service.GradeService;
import com.dev.makov.rl_system.service.SchoolService;
import com.dev.makov.rl_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SchoolController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/admin/addSchool")
    public String showAddSchoolForm(Model model) {
        model.addAttribute("school", new School());
        return "admin/addSchool";
    }

    @PostMapping("/admin/addSchoolProcess")
    public String addSchool(@ModelAttribute("school") School school) {
        userService.addSchool(school);
        return "redirect:/admin/home";
    }


    @GetMapping("/school/list")
    public String showSchools(Model model){
        List<School> schools = schoolService.findAll();
        for (School school : schools) {
            List<User> students = userService.findStudentsBySchool(school.getId());
            school.setStudents(new HashSet<>(students)); // Assuming School has a set of students
        }
        model.addAttribute("schools", schools);
        return "/school/list-schools";
    }

    @GetMapping("/school/addDirector")
    public String showAddDirectorForm(@RequestParam("school_id") Long schoolId, Model model) {
        User director = new User();
        model.addAttribute("director", director);
        model.addAttribute("schoolId", schoolId);
        return "director/addDirector";
    }

    @PostMapping("/school/processAddDirector")
    public String processAddDirector(@ModelAttribute("director") User director, @RequestParam("schoolId") Long schoolId, Model model) {
        // Fetch the school
        School school = schoolService.findById(schoolId);

        // Register the director with the associated school
        userService.registerDirector(director, school);

        // Associate director with the school
        school.setDirector(director);
        schoolService.save(school);

        return "redirect:/school/list";
    }

    @GetMapping("/school/updateDirector")
    public String showUpdateDirectorForm(@RequestParam("director_id") Long directorId, Model model) {
        User director = userService.findById(directorId);
        model.addAttribute("director", director);
        return "director/updateDirector";
    }

    @PostMapping("/school/processUpdateDirector")
    public String processUpdateDirector(@ModelAttribute("director") User director) {
        // Update the director information
        userService.updateDirector(director);
        return "redirect:/school/list";
    }

    @GetMapping("/school/deleteDirector")
    public String deleteDirector(@RequestParam("director_id") Long directorId, @RequestParam("school_id") Long schoolId) {
        if (directorId != 0) {
            // Delete the director from the user table
            userService.deleteDirector(directorId);

            // Remove the director from the school table
            School school = schoolService.findById(schoolId);
            if (school != null) {
                school.setDirector(null);
                schoolService.save(school);
            }
        }

        return "redirect:/school/list";
    }

    @GetMapping("/school/displayDirector")
    public String displayDirector(@RequestParam("director_id") Long directorId, Model model){
       User director = userService.findById(directorId);
       model.addAttribute("director", director);
       return "director/displayDirector";
    }

    @GetMapping("/school/addTeacher")
    public String showAddTeacherForm(@RequestParam("school_id") Long schoolId, Model model) {
        User teacher = new User();
        List<SchoolSubject> subjects = schoolSubjectRepository.findAll();
        model.addAttribute("teacher", teacher);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("subjects", subjects);
        return "teacher/addTeacher";
    }

    @PostMapping("/school/processAddTeacher")
    public String processAddTeacher(@ModelAttribute("teacher") User teacher, @RequestParam("schoolId") Long schoolId, @RequestParam("subjectIds") Set<Long> subjectIds, Model model) {
        School school = schoolService.findById(schoolId);
        teacher.setSchool(school);
        userService.registerTeacher(teacher, subjectIds);
        return "redirect:/school/list";
    }

    @GetMapping("/school/updateTeacher")
    public String showUpdateTeacherForm(@RequestParam("school_id") Long schoolId, Model model) {
        User teacher = new User();
        List<SchoolSubject> subjects = schoolSubjectRepository.findAll();
        model.addAttribute("teacher", teacher);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("subjects", subjects);
        return "teacher/updateTeacher";
    }

    @PostMapping("/school/processUpdateTeacher")
    public String processUpdateTeacher(@ModelAttribute("teacher") User teacher, @RequestParam("subjectIds") Set<Long> subjectIds) {
        userService.updateTeacher(teacher, subjectIds);
        return "redirect:/school/list";
    }

    @GetMapping("/school/deleteTeacher")
    public String showDeleteTeacherForm(@RequestParam("school_id") Long schoolId, Model model) {
        model.addAttribute("schoolId", schoolId);
        return "teacher/deleteTeacher";
    }

    @PostMapping("/school/processDeleteTeacher")
    public String processDeleteTeacher(@RequestParam("teacherId") Long teacherId, @RequestParam("schoolId") Long schoolId) {
        // Remove the teacher from the school_teachers table
        schoolService.removeTeacherFromSchool(teacherId, schoolId);

        // Delete the teacher from the user table
        userService.deleteTeacher(teacherId);

        return "redirect:/school/list";
    }

    @GetMapping("/school/displayTeachers")
    public String displayTeachers(@RequestParam("school_id") Long schoolId, Model model) {
        // Fetch the school
        School school = schoolService.findById(schoolId);

        // Get the list of teachers
        List<User> teachers = userService.findTeachersBySchool(schoolId);
        model.addAttribute("teachers", teachers);

        return "teacher/displayTeachers";
    }


    @GetMapping("/school/addStudent")
    public String showAddStudentForm(@RequestParam("school_id") Long schoolId, Model model) {
        User student = new User();
        model.addAttribute("student", student);
        model.addAttribute("schoolId", schoolId);
        return "student/addStudent";
    }

    @PostMapping("/school/processAddStudent")
    public String processAddStudent(@ModelAttribute("student") User student, @RequestParam("schoolId") Long schoolId) {
        userService.registerStudentToSchool(student, schoolId);
        return "redirect:/school/list";
    }

    @GetMapping("/school/updateStudent")
    public String showUpdateStudentForm(@RequestParam("school_id") Long schoolId, Model model) {
        User student = new User();  // Create a new user object to bind the form
        model.addAttribute("student", student);
        model.addAttribute("schoolId", schoolId);  // Pass the school ID to the form
        return "student/updateStudent";
    }

    @GetMapping("/school/fetchStudentForUpdate")
    public String fetchStudentForUpdate(@RequestParam("studentId") Long studentId, @RequestParam("school_id") Long schoolId, Model model) {
        User student = userService.findById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("schoolId", schoolId);  // Pass the school ID back to the form
        return "student/updateStudentDetails";  // A new form to display and update the student details
    }



    @PostMapping("/school/processUpdateStudent")
    public String processUpdateStudent(@ModelAttribute("student") User student) {
        userService.updateStudent(student);
        return "redirect:/school/list";
    }

    @GetMapping("/school/deleteStudent")
    public String showDeleteStudentForm(@RequestParam("school_id") Long schoolId, Model model) {
        model.addAttribute("schoolId", schoolId);
        return "student/deleteStudent";
    }

    @PostMapping("/school/processDeleteStudent")
    public String processDeleteStudent(@RequestParam("studentId") Long studentId, @RequestParam("schoolId") Long schoolId) {
        // Remove the student from the school_students table
        schoolService.removeStudentFromSchool(studentId, schoolId);

        // Delete the student from the user table
        userService.deleteStudent(studentId);

        return "redirect:/school/list";
    }

    @GetMapping("/school/students")
    public String showStudents(@RequestParam("school_id") Long schoolId, Model model) {
        List<User> students = userService.findStudentsBySchool(schoolId);
        model.addAttribute("students", students);
        return "student/students-list";
    }

    @GetMapping("/school/addParent")
    public String showAddParentForm(@RequestParam("student_id") Long studentId, Model model) {
        User parent = new User();
        model.addAttribute("parent", parent);
        model.addAttribute("studentId", studentId);
        return "parent/addParent";
    }

    @PostMapping("/school/processAddParent")
    public String processAddParent(@ModelAttribute("parent") User parent, @RequestParam("studentId") Long studentId) {
        userService.registerParentWithStudent(parent, studentId);
        return "redirect:/school/list";
    }


    @GetMapping("/school/updateParent")
    public String showUpdateParentForm(@RequestParam("parent_id") Long parentId, Model model) {
        User parent = userService.findById(parentId);
        model.addAttribute("parent", parent);
        return "parent/updateParent";
    }


    @PostMapping("/school/processUpdateParent")
    public String processUpdateParent(@ModelAttribute("parent") User parent) {
        userService.updateParent(parent);
        return "redirect:/school/list";
    }


    @GetMapping("/school/fetchParentForUpdate")
    public String fetchParentForUpdate(@RequestParam("parentId") Long parentId, Model model) {
        User parent = userService.findById(parentId);
        model.addAttribute("parent", parent);
        return "parent/updateParent"; // Redirect to the update form with populated data
    }

    @GetMapping("/school/deleteParent")
    public String showDeleteParentForm(@RequestParam("parent_id") Long parentId, @RequestParam("student_id") Long studentId, Model model) {
        model.addAttribute("parentId", parentId);
        model.addAttribute("studentId", studentId);
        return "parent/deleteParent";
    }
    @PostMapping("/school/processDeleteParent")
    public String processDeleteParent(@RequestParam("parentId") Long parentId, Model model) {
        User parent = userService.findById(parentId);
        if (parent != null && "ROLE_PARENT".equals(parent.getUserRole())) {
            // If the user has the role PARENT, proceed with deletion
            for (User student : parent.getSchool().getStudents()) {
                userService.removeParentFromStudent(parentId, student.getId());
            }
            userService.deleteParent(parentId);
            return "redirect:/school/list";
        } else {
            // If the user is not a parent, show an error message
            model.addAttribute("errorMessage", "The specified ID does not belong to a parent.");
            return "parent/deleteParent";
        }
    }

    @GetMapping("/school/displayParents")
    public String displayParent(@RequestParam("school_id") Long schoolId, Model model){
        List<User> parents = userService.findParentsBySchool(schoolId);
        model.addAttribute("parents", parents);
        return "parent/displayParents";

    }

    @GetMapping("/school/addGrade")
    public String showAddGradeForm(@RequestParam("student_id") Long studentId, Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("studentId", studentId);
        model.addAttribute("subjects", schoolSubjectRepository.findAll());
        return "grade/addGrade";
    }

    @PostMapping("/school/processAddGrade")
    public String processAddGrade(@RequestParam("grade") int gradeValue, @RequestParam("studentId") Long studentId, @RequestParam("subjectId") Long subjectId) {
        // Fetch the correct Grade entity by its value
        Grade grade = gradeService.findGradeByValue(gradeValue);
        gradeService.addGradeToStudent(grade.getId(), studentId, subjectId);
        return "redirect:/school/students?school_id=" + studentId;
    }


    @GetMapping("/school/displayGrades")
    public String displayGrades(@RequestParam("student_id") Long studentId, Model model) {
        User student = userService.findById(studentId);
        List<StudentGrade> grades = gradeService.findGradesByStudentId(studentId);
        model.addAttribute("student", student);
        model.addAttribute("grades", grades);
        return "grade/displayGrades";
    }




}
