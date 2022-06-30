package com.example.apiserver;

import com.example.apiserver.entity.Cust;
import com.example.apiserver.repository.CustRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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

    public static String custId() {
        return MDC.get("custId");
    }

    public static String osType() {
        String[] uas = devs();
        return uas[2];
    }

    /**
     * uas : [0]APPversion; [1]DEVname; [2]OStype; [3]OSversion
     * @return
     */
    public static String[] devs() {
        String ua = MDC.get("ua");
        if (ua == null) return null;

        return ua.split(";");
    }


}
