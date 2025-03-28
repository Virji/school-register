package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.entity.Semester;
import com.dev.makov.rl_system.dao.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    private SemesterRepository semesterRepository;

    @GetMapping("/list")
    public String listSemesters(Model model) {
        model.addAttribute("semesters", semesterRepository.findAll());
        return "semester/listSemesters";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("semester", new Semester());
        return "semester/addSemester";
    }

    @PostMapping("/addProcess")
    public String addSemester(@ModelAttribute("semester") Semester semester) {
        semesterRepository.save(semester);
        return "redirect:/semester/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid semester Id:" + id));
        model.addAttribute("semester", semester);
        return "semester/editSemester";
    }

    @PostMapping("/update/{id}")
    public String updateSemester(@PathVariable("id") Long id, @ModelAttribute("semester") Semester semester) {
        Semester existingSemester = semesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid semester Id:" + id));

        existingSemester.setName(semester.getName());
        existingSemester.setStartDate(semester.getStartDate());
        existingSemester.setEndDate(semester.getEndDate());

        semesterRepository.save(existingSemester);
        return "redirect:/semester/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSemester(@PathVariable("id") Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid semester Id:" + id));
        semesterRepository.delete(semester);
        return "redirect:/semester/list";
    }

}
