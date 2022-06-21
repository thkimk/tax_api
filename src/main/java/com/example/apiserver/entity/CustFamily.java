package com.example.apiserver.entity;

import com.example.apiserver.Constants;
import com.example.apiserver.Utils;
import com.example.apiserver.vo.SaveFamilyVo;
import com.example.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "cust_family") // 'user' 테이블과 매핑됨을 명시한다.
public class CustFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long familySeq;

    @Column(name="cust_id", length = 9)
    private String custId;

    @Column(name="family", length = 2)
    private String family;

    @Column(name="birth")
    private LocalDate birth;

    @Column(name="is_disorder")
    private char isDisorder;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    public CustFamily(String custId, SaveFamilyVo.Family family) {
        this.custId = custId;
        this.family = family.getFamily();
        this.birth = LocalDate.parse(family.getBirth());
        this.isDisorder = family.getIsDisorder();

        this.createDt = LocalDateTime.now();
    }

}