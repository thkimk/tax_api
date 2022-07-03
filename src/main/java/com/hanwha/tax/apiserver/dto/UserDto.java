package com.hanwha.tax.apiserver.dto;

//import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long msrl;
    private String uid;
    private String name;
    private int age;


/*
    @QueryProjection
    public UserDto(Long msrl, String uid, String name, int age) {
        this.msrl = msrl;
        this.uid = uid;
        this.name = name;
        this.age = age;
    }
*/
}
