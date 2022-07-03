package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.dto.TermsDto;
import com.hanwha.tax.apiserver.entity.Industry;
import com.hanwha.tax.apiserver.repository.IndustryRepository;
import com.hanwha.tax.apiserver.repository.TermsRepository;
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
        termsDto.fill(termsRepository.findAllByViewYnOrderByType('Y'));

        return termsDto;
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

