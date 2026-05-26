package com.example.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Added import
import org.springframework.web.bind.annotation.RequestBody; // Added import
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.dto.CertificateRequest;
import com.example.school.dto.CertificateResponse;
import com.example.school.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/certificate")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
public class CertificateController {
    private final CertificateService certificateService;

    @GetMapping
    List<CertificateResponse> getAll() {
        return certificateService.getAllCertificate();
    }

    // FIXED: Added @RequestBody to intercept React JSON data
    @PostMapping
    CertificateResponse createCertificate(@RequestBody CertificateRequest req) {
        return certificateService.createCertificate(req);
    }

    // FIXED: Changed from @GetMapping to @PutMapping, and added @RequestBody
    @PutMapping("/{id}")
    CertificateResponse updateCertificate(@PathVariable Long id, @RequestBody CertificateRequest req) {
        return certificateService.updateCertificate(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
    }
}