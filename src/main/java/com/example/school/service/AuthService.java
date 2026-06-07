package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.messaging.simp.SimpMessagingTemplate; // 1. នាំចូលកញ្ចប់នេះ
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.school.dto.LoginRequest;
import com.example.school.dto.LoginResponse;
import com.example.school.dto.NotificationResponse; // ត្រូវប្រាកដថាមាន DTO នេះ
import com.example.school.dto.RegisterRequest;
import com.example.school.entity.ClassRoom;
import com.example.school.entity.Course;
import com.example.school.entity.Notification;
import com.example.school.entity.Student;
import com.example.school.entity.Teacher;
import com.example.school.entity.User;
import com.example.school.entity.enums.Gender;
import com.example.school.entity.enums.Role;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.NotificationRepository; // 2. នាំចូល Repository នេះ
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
        private final NotificationRepository notificationRepository; // ចាក់បញ្ចូលដើម្បីលុករក្សាទុកក្នុង DB
        private final SimpMessagingTemplate messagingTemplate; // ចាក់បញ្ចូលដើម្បីបាញ់ WebSocket
        private final PasswordEncoder passwordEncoder;

        @Transactional
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

                // បង្កើតអថេរទុកសម្រាប់បង្កើតសារផ្ញើទៅ Navbar តាមប្រភេទ Role
                String notifTitle = "New Registration!";
                String notifMessage = "A new user has registered.";

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
                        student.setGender(Gender.MALE);
                        student.setPhone(req.getPhone());
                        student.setClazz(clazz);
                        student.setCourse(course);

                        studentRepository.save(student);

                        // កំណត់ខ្លឹមសារសារសម្រាប់ Student
                        notifTitle = "New Student Enrollment!";
                        notifMessage = "Student " + req.getFirstName() + " " + req.getLastName()
                                        + " has successfully enrolled.";
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

                        teacher.setUser(savedUser);

                        teacherRepository.save(teacher);

                        // កំណត់ខ្លឹមសារសារសម្រាប់ Teacher
                        notifTitle = "New Teacher Onboarding!";
                        notifMessage = "Teacher " + req.getFirstName() + " " + req.getLastName()
                                        + " has joined the academy.";
                }

                // ========================================================
                // ដំណើរការបង្កើត និង បាញ់បញ្ជូន NOTIFICATION ទៅកាន់ NAVBAR ភ្លាមៗ
                // ========================================================
                Notification notification = Notification.builder()
                                .title(notifTitle)
                                .message(notifMessage)
                                .createdAt(LocalDateTime.now())
                                .isRead(false)
                                .build();

                // រក្សាទុកក្នុង Database សិនដើម្បីឱ្យទិន្នន័យមាន ID ត្រឹមត្រូវ
                notificationRepository.save(notification);

                // បំលែង Entity ទៅជា Response DTO រួចផ្ញើតាមទម្រង់ WebSocket
                NotificationResponse response = NotificationResponse.builder()
                                .id(notification.getId())
                                .title(notification.getTitle())
                                .message(notification.getMessage())
                                .isRead(notification.isRead())
                                .createdAt(notification.getCreatedAt())
                                .build();

                // បាញ់បញ្ជូនសារ real-time ទៅកាន់ frontend Navbar ដែលកំពុង Subscribe ផ្លូវ
                // "/topic/notifications"
                messagingTemplate.convertAndSend("/topic/notifications", response);

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