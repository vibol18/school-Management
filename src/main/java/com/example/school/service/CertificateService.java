package com.example.school.service;

import java.util.List;
import com.example.school.dto.CertificateRequest;
import com.example.school.dto.CertificateResponse;

public interface CertificateService {

    CertificateResponse createCertificate(CertificateRequest request);

    List<CertificateResponse> getAllCertificate();

    CertificateResponse getByID(Long id);

    CertificateResponse updateCertificate(Long id, CertificateRequest request);

    void deleteCertificate(Long id);
}