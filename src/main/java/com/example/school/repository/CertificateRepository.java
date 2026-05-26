package com.example.school.repository;

// FIX: Import your own database Entity, NOT the Java security package
import com.example.school.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}