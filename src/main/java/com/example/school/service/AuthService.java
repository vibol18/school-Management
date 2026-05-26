package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.school.dto.LoginRequest;
import com.example.school.dto.LoginResponse;
import com.example.school.dto.RegisterRequest;
import com.example.school.entity.ClassRoom;
import com.example.school.entity.Course;
import com.example.school.entity.Student;
import com.example.school.entity.Teacher;
import com.example.school.entity.User;
import com.example.school.entity.enums.Gender;
import com.example.school.entity.enums.Role;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final TeacherRepository teacherRepository;
        private final UserRepository userRepository;
        private final StudentRepository studentRepository;
        private final ClassRoomrepository classRoomrepository;
        private final CourseRepository courseRepository;
        private final PasswordEncoder passwordEncoder;

        @Transactional // Added to ensure both User and Profile save smoothly together
        public String register(RegisterRequest req) {

                // SAVE CREDENTIALS USER
                User user = User.builder()
                                .username(req.getUsername())
                                .email(req.getEmail())
                                .password(passwordEncoder.encode(req.getPassword()))
                                .role(req.getRole())
                                .enabled(true)
                                .build();

                User savedUser = userRepository.save(user);

                // STUDENT REGISTRATION PIPELINE
                if (req.getRole() == Role.STUDENT) {

                        ClassRoom clazz = classRoomrepository.findById(req.getClassId())
                                        .orElseThrow(() -> new RuntimeException("Class not found"));

                        Course course = courseRepository.findById(req.getCourseId())
                                        .orElseThrow(() -> new RuntimeException("Course not found"));

                        Student student = new Student();
                        String studentCode = "STU" + System.currentTimeMillis();

                        student.setStudentCode(studentCode);
                        student.setFirstName(req.getFirstName());
                        student.setLastName(req.getLastName());
                        student.setGender(Gender.MALE); // Consider adding req.getGender() if needed later
                        student.setPhone(req.getPhone());
                        student.setClazz(clazz);
                        student.setCourse(course);

                        // Optional: Link student profile back to the saved credentials user
                        // student.setUser(savedUser);

                        studentRepository.save(student);
                }

                // TEACHER REGISTRATION PIPELINE
                if (req.getRole() == Role.TEACHER) {

                        Teacher teacher = new Teacher();

                        teacher.setTeacherCode("TEA-" + System.currentTimeMillis());
                        teacher.setFirstName(req.getFirstName());
                        teacher.setLastName(req.getLastName());
                        teacher.setEmail(req.getEmail());
                        teacher.setPhone(req.getPhone());
                        teacher.setSalary(BigDecimal.ZERO);

                        // FIX: Pointing to savedUser instead of the teacher instance itself
                        teacher.setUser(savedUser);

                        teacherRepository.save(teacher);
                }

                return "Register Successfully";
        }

        // =========================
        // LOGIN
        // =========================
        public LoginResponse login(LoginRequest request) {

                User user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                boolean isPasswordMatch = passwordEncoder.matches(
                                request.getPassword(),
                                user.getPassword());

                if (!isPasswordMatch) {
                        throw new RuntimeException("Invalid password");
                }

                LoginResponse.UserResponse userPayload = LoginResponse.UserResponse.builder()
                                .id(user.getId())
                                .name(user.getUsername())
                                .email(user.getEmail())
                                .role(user.getRole().name())
                                .build();

                return LoginResponse.builder()
                                .token("mock-jwt-token-here")
                                .user(userPayload)
                                .build();
        }
}