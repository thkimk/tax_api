package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.advice.exception.InvalidInputValueException;
import com.hanwha.tax.apiserver.dto.*;
import com.hanwha.tax.apiserver.entity.Faq;
import com.hanwha.tax.apiserver.entity.Terms;
import com.hanwha.tax.apiserver.repository.FaqRepository;
import com.hanwha.tax.apiserver.repository.IndustryRepository;
import com.hanwha.tax.apiserver.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoService {

    @Autowired
    TermsRepository termsRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    FaqRepository faqRepository;

    public List<TermsDto> getTermsList() {
        final List<Terms> termsList = termsRepository.findAllOrderByType();

        TermsDto termsDto = null;
        final List<TermsDto> termsListDto = new ArrayList<>();
        for (Terms terms : termsList) {
            int typeLen = terms.getType().length();
            if (typeLen == 1) {
                termsDto = new TermsDto();
                termsDto.setTerms(terms);
                termsListDto.add(termsDto);
            } else if (typeLen == 2) {
                if (termsDto == null || !terms.getType().startsWith(termsDto.getType())) {
                    throw new InvalidInputValueException();
                }
                if (termsDto.getChild() == null) {
                    termsDto.setChild(new ArrayList<>());
                }

                final TermsDto childTerms = new TermsDto();
                childTerms.setTerms(terms);
                termsDto.getChild().add(childTerms);
            }
        }

        return termsListDto;
    }

    public TermsDetailDto getTerms(Long termsId) {
        final Optional<Terms> terms = termsRepository.findById(termsId);

        if (terms.isPresent()) {
            final TermsDetailDto termsDetail = new TermsDetailDto();
            termsDetail.setTerms(terms.get());
            return termsDetail;
        } else {
            return null;
        }
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
    public List<JobInterface> jobs(String code, String name) {
        final List<JobInterface> industries;

        if (code != null) {
            industries = industryRepository.selectAllByCode(code);
        } else if (name != null) {
            industries = industryRepository.selectAllByName(name);
        } else {
            industries = industryRepository.selectAll();
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

    public PageableDto<FaqDto> getFaqList(Pageable pageable) {
        final Page<Faq> faqList = faqRepository.findAll(pageable);
        List<FaqDto> faqDtoList = new ArrayList<>();
        faqList.getContent().forEach(faq -> {
            FaqDto faqDto = new FaqDto();
            faqDto.setFaq(faq);
            faqDtoList.add(faqDto);
        });

        final PageableDto pageDto = new PageableDto(faqDtoList, faqList);
        return pageDto;
    }

    public FaqDto getFaq(Long id) {
        final Optional<Faq> faq = faqRepository.findById(id);
        if (faq.isPresent()) {
            FaqDto faqDto = new FaqDto();
            faqDto.setFaq(faq.get());
            return faqDto;
        } else {
            return null;
        }
    }
}

