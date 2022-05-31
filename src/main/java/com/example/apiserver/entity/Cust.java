package com.example.apiserver.entity;

import com.example.apiserver.Constants;
import com.example.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "cust") // 'user' 테이블과 매핑됨을 명시한다.
public class Cust {

    @Id
    @Column(length = 9)
    private String cust_id;

    @Column(length = 2)
    private String cust_status;

    @Column(length = 2)
    private String cust_grade;

    @Column()
    private LocalDateTime asct_in_dt;

    @Column()
    private LocalDateTime asct_out_dt;

    @Column()
    private LocalDateTime reg_in_dt;

    @Column()
    private LocalDateTime reg_out_dt;

    @Column()
    private LocalDateTime create_dt;

    @Column()
    private LocalDateTime update_dt;

    // 회원가입시 생성
    Cust(SignupVo signupVo) {
        this.cust_id = "0000";
        this.cust_status = Constants.CUST_ST_NORMAL;
        this.cust_grade = Constants.CUST_GR_ASOC;

    }

}
