package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.dao.ClassRepository;
import com.dev.makov.rl_system.dao.ScheduleRepository;
import com.dev.makov.rl_system.dao.SchoolSubjectRepository;
import com.dev.makov.rl_system.dao.SemesterRepository;
import com.dev.makov.rl_system.entity.Class;
import com.dev.makov.rl_system.entity.Schedule;
import com.dev.makov.rl_system.entity.SchoolSubject;
import com.dev.makov.rl_system.entity.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SchoolSubjectRepository subjectRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/view")
    public String showScheduleForm(Model model) {
        List<Class> classes = classRepository.findAll();
        List<SchoolSubject> subjects = subjectRepository.findAll();
        List<Semester> semesters = semesterRepository.findAll();

        List<String> timeSlots = Arrays.asList("08:00 - 09:40", "09:50 - 10:30", "10:40 - 11:20", "11:40 - 12:20",
                "12:30 - 13:10", "13:20 - 14:00", "14:05 - 14:45", "14:50 - 15:30");
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        model.addAttribute("classes", classes);
        model.addAttribute("subjects", subjects);
        model.addAttribute("semesters", semesters);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("daysOfWeek", daysOfWeek);
        model.addAttribute("schedule", new Schedule());
        return "schedule/schedule";
    }

    @PostMapping("/save")
    public String saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return "redirect:/schedule/view";
    }
}
