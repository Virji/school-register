package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.*;
import com.dev.makov.rl_system.entity.*;
import com.dev.makov.rl_system.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ID_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public void registerStudent(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (user.getSchool() == null) {
            throw new IllegalArgumentException("School cannot be null");
        }
        if (user.getClass() == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_STUDENT");
        user.setRoles(new HashSet<>(Set.of(userRole)));
        user.setUserRole(userRole.getName());
        user.setStudentId(generateStudentId());
        userRepository.save(user);
    }



    public void registerStudentToSchool(User student, Long schoolId) {
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found"));
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        Role studentRole = roleRepository.findByName("ROLE_STUDENT");
        student.setRoles(new HashSet<>(Set.of(studentRole)));
        student.setUserRole(studentRole.getName());
        student.setSchool(school);
        student.setStudentId(generateStudentId());
        userRepository.save(student);
        addStudentToSchool(student.getId(), schoolId);
    }

    private void addStudentToSchool(Long studentId, Long schoolId) {
        // Assuming you have a method in your repository to handle the insert
        userRepository.addStudentToSchool(studentId, schoolId);
    }

    public void updateStudent(User student) {
        User existingStudent = userRepository.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        if(existingStudent.getaClass() == null || existingStudent.getaClass().equals("")){
            existingStudent.setaClass(student.getaClass());
            classRepository.saveClassStudent(existingStudent.getaClass().getId(), existingStudent.getId());
        }
        existingStudent.setaClass(student.getaClass());
        if (!student.getPassword().isEmpty()) {
            existingStudent.setPassword(passwordEncoder.encode(student.getPassword()));
        }
        userRepository.save(existingStudent);
    }


    public void deleteStudent(Long studentId) {
        userRepository.deleteById(studentId);
    }

    public void saveStudentSubjects(Long studentId, List<Long> schoolSubjectIds) {
        for (Long subjectId : schoolSubjectIds) {
            userRepository.addSchoolSubjectToStudent(studentId, subjectId);
        }
    }


    public void registerDirector(User user, School school) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_DIRECTOR");
        user.setRoles(new HashSet<>(Set.of(userRole)));
        user.setUserRole(userRole.getName());
        user.setSchool(school); // Set the school
        userRepository.save(user);
    }

    public void updateDirector(User director) {
        User existingDirector = userRepository.findById(director.getId()).orElse(null);
        if (existingDirector != null) {
            existingDirector.setFirstName(director.getFirstName());
            existingDirector.setLastName(director.getLastName());
            existingDirector.setEmail(director.getEmail());
            // Encrypt the password before saving
            if (!director.getPassword().isEmpty()) {
                existingDirector.setPassword(passwordEncoder.encode(director.getPassword()));
            }
            userRepository.save(existingDirector);
        } else {
            throw new IllegalArgumentException("Director not found");
        }
    }
    public void deleteDirector(Long directorId) {
        userRepository.deleteById(directorId);
    }


    public void addTeacherToSchool(User teacher, School school) {
        school.getTeachers().add(teacher);
        schoolRepository.save(school);
    }

    public void registerTeacher(User teacher, Set<Long> subjectIds) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        Role teacherRole = roleRepository.findByName("ROLE_TEACHER");
        teacher.setRoles(new HashSet<>(Set.of(teacherRole)));
        teacher.setUserRole(teacherRole.getName());

        // Fetch and set subjects
        Set<SchoolSubject> subjects = new HashSet<>(schoolSubjectRepository.findAllById(subjectIds));
        teacher.setSchoolSubjects(subjects);

        userRepository.save(teacher);
    }

    public void updateTeacher(User teacher, Set<Long> subjectIds) {
        User existingTeacher = userRepository.findById(teacher.getId()).orElse(null);
        if (existingTeacher != null) {
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            existingTeacher.setEmail(teacher.getEmail());
            if (!teacher.getPassword().isEmpty()) {
                existingTeacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            }

            // Update subjects
            Set<SchoolSubject> subjects = new HashSet<>(schoolSubjectRepository.findAllById(subjectIds));
            existingTeacher.setSchoolSubjects(subjects);

            userRepository.save(existingTeacher);
        } else {
            throw new IllegalArgumentException("Teacher not found");
        }
    }


    public void deleteTeacher(Long teacherId) {
        userRepository.deleteById(teacherId);
    }

    public School findSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found - " + schoolId));
    }


    public void registerParent(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_PARENT");
        user.setRoles(new HashSet<>(Set.of(userRole)));
        user.setUserRole(userRole.getName());
        userRepository.save(user);
        userRepository.updateUserRoleName(user.getId(), userRole.getId(), userRole.getName());
    }

    public void registerParentWithStudent(User parent, Long studentId) {
        if (parent.getPassword() == null || parent.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        School school = student.getSchool();
        parent.setSchool(school);

        parent.setPassword(passwordEncoder.encode(parent.getPassword()));
        Role parentRole = roleRepository.findByName("ROLE_PARENT");
        parent.setRoles(new HashSet<>(Set.of(parentRole)));
        parent.setUserRole(parentRole.getName());
        userRepository.save(parent);

        // Add to parent_student table
        addParentToStudent(parent.getId(), studentId);
    }

    private void addParentToStudent(Long parentId, Long studentId) {
        userRepository.addParentToStudent(parentId, studentId);
    }

    public void updateParent(User parent) {
        User existingParent = userRepository.findById(parent.getId())
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        existingParent.setFirstName(parent.getFirstName());
        existingParent.setLastName(parent.getLastName());
        existingParent.setEmail(parent.getEmail());
        if (parent.getPassword() != null && !parent.getPassword().isEmpty()) {
            existingParent.setPassword(passwordEncoder.encode(parent.getPassword()));
        }
        userRepository.save(existingParent);
    }


    public void deleteParent(Long parentId) {
        userRepository.deleteById(parentId);
    }

    public void removeParentFromStudent(Long parentId, Long studentId) {
        userRepository.removeParentFromStudent(parentId, studentId);
    }

    public List<User> findParentsBySchool(Long schoolId) {
        return userRepository.findBySchoolIdAndUserRole(schoolId, "ROLE_PARENT");
    }


    public void addSchool(School school) {
        schoolRepository.save(school);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private String generateStudentId() {
        StringBuilder sb = new StringBuilder();
        sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }


    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find class id - " + id);
        }
        return theUser;
    }


    public void removeStudentFromClass(Long classId, Long studentId) {
        System.out.println("Removing student with ID: " + studentId + " from class with ID: " + classId);
        classRepository.removeStudentFromClass(classId, studentId);
    }

    public List<User> findTeachersBySchool(Long schoolId) {
        return userRepository.findBySchoolIdAndUserRole(schoolId, "ROLE_TEACHER");
    }

    public List<User> findStudentsBySchool(Long schoolId) {
        return userRepository.findBySchoolIdAndUserRole(schoolId, "ROLE_STUDENT");
    }

    public List<User> findStudentsByClassId(Long classId) {
        return userRepository.findStudentsByClassId(classId);
    }


}
