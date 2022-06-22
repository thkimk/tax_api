package com.example.apiserver;

import com.example.apiserver.entity.Cust;
import com.example.apiserver.repository.CustRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
public class Utils {


    public static void logCalled(String op, Object object) {
        if (object == null) log.info("## [CALLED] {}() : null", op);
        else log.info("## [CALLED] {}() : {}", op, object.toString());
    }


    public static void logCallReturned(String op, Object object) {
        if (object == null) log.info("## [RETURN] {}() : null", op);
        else log.info("## [RETURN] {}() : {}", op, object.toString());
    }


}
