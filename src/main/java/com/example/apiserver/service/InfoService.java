package com.example.apiserver.service;

import com.example.apiserver.dto.IndustryDto;
import com.example.apiserver.dto.JobsDto;
import com.example.apiserver.dto.TermsDto;
import com.example.apiserver.entity.Industry;
import com.example.apiserver.entity.NotiMsg;
import com.example.apiserver.entity.Terms;
import com.example.apiserver.repository.IndustryRepository;
import com.example.apiserver.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoService {

    @Autowired
    TermsRepository termsRepository;

    @Autowired
    IndustryRepository industryRepository;


    public List<Terms> terms() {
        return termsRepository.findAllByViewYn('Y');
    }


/*
    public JobsDto jobs(String code, String name) {
        JobsDto jobsDto = new JobsDto();
        List<Industry> industries = industryRepository.findAll();

        for (Industry industry : industries) {
        }

        return jobsDto;
    }
*/
    public List<Industry> jobs(String code, String name) {
        List<Industry> industries = null;

        if (code != null) {
            industries = industryRepository.findAllByCode(code);
        } else if (name != null) {
            industries = industryRepository.findAllByName(name);
        } else {
            industries = industryRepository.findAll();
        }

        return industries;
    }
/*
    public List<Industry> jobs(String code, String name) {
        List<JobsDto> jobsDtos = new ArrayList<>();
        List<Industry> notiMsgs = industryRepository.findAll();

        jobsDtos.fill(appInfos);
        return industryRepository.findAll();
    }
*/

}

