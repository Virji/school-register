package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.dao.*;
import com.dev.makov.rl_system.entity.*;
import com.dev.makov.rl_system.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumRepository curriculumRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private SchoolSubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SemesterRepository semesterRepository;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("teachers", userRepository.findAll());
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("curriculum", new Curriculum());
        return "curriculums/addCurriculum";
    }

    @PostMapping("/save")
    public String saveCurriculum(@ModelAttribute("curriculum") Curriculum curriculum, RedirectAttributes redirectAttributes) {
        try {
            curriculumRepository.save(curriculum);
            redirectAttributes.addFlashAttribute("successMessage", "Curriculum successfully saved!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/curriculum/list";
    }

    @GetMapping("/list")
    public String listCurriculums(Model model) {
        model.addAttribute("curriculums", curriculumRepository.findAll());
        return "curriculums/listCurriculums";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curriculum Id:" + id));

        List<Class> classes = classRepository.findAll();
        List<SchoolSubject> subjects = subjectRepository.findAll();
        List<User> teachers = userRepository.findAll();
        List<School> schools = schoolRepository.findAll();
        List<Semester> semesters = semesterRepository.findAll();

        model.addAttribute("classes", classes);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("schools", schools);
        model.addAttribute("semesters", semesters);
        model.addAttribute("curriculum", curriculum);

        return "curriculums/editCurriculum";
    }

    @PostMapping("/update/{id}")
    public String updateCurriculum(@PathVariable("id") Long id, @ModelAttribute("curriculum") Curriculum curriculum, RedirectAttributes redirectAttributes) {
        curriculumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curriculum Id:" + id));

        try {
            curriculum.setId(id);
            curriculumRepository.save(curriculum);
            redirectAttributes.addFlashAttribute("successMessage", "Curriculum successfully updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/curriculum/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCurriculum(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curriculum Id:" + id));

        try {
            curriculumRepository.delete(curriculum);
            redirectAttributes.addFlashAttribute("successMessage", "Curriculum successfully deleted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/curriculum/list";
    }

}