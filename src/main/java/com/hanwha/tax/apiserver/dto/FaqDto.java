package com.hanwha.tax.apiserver.dto;


import com.hanwha.tax.apiserver.entity.Faq;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class FaqDto {
    private long id;

    private String type;

    private String question;

    @Nullable
    private String answer;

    public void setFaq(Faq faq) {
        id = faq.getId();
        type = faq.getType();
        question = faq.getQuestion();
        answer = faq.getContent();
    }
}