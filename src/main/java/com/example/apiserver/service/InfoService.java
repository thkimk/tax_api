package com.example.apiserver.service;

import com.example.apiserver.dto.JobsDto;
import com.example.apiserver.dto.TermsDto;
import com.example.apiserver.entity.Industry;
import com.example.apiserver.entity.Terms;
import com.example.apiserver.repository.IndustryRepository;
import com.example.apiserver.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoService {

    @Autowired
    TermsRepository termsRepository;

    @Autowired
    IndustryRepository industryRepository;


    public TermsDto terms() {
        TermsDto termsDto = new TermsDto();
        List<Terms> terms = termsRepository.findAll();

        return termsDto;
    }


    public JobsDto jobs(String code, String name) {
        JobsDto jobsDto = new JobsDto();
        List<Industry> terms = industryRepository.findAll();

        return jobsDto;
    }

}

