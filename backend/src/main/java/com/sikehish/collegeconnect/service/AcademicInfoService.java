package com.sikehish.collegeconnect.service;

import com.sikehish.collegeconnect.model.AcademicInfo;
import com.sikehish.collegeconnect.repository.AcademicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicInfoService {

    @Autowired
    private AcademicInfoRepository academicInfoRepository;

    public List<AcademicInfo> getAcademicInfoByStudentUsername(String studentUsername) {
        return academicInfoRepository.findByStudentUsername(studentUsername);
    }
}
