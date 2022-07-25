package com.hanwha.tax.apiserver.batch;


import com.hanwha.tax.apiserver.model.Coocon;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.service.MydataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
@Slf4j
public class TaxBatch {
    private MydataService mydataService;

    private Coocon coocon;

    @Autowired
    public TaxBatch(MydataService mydataService, Coocon coocon) {
        this.mydataService = mydataService;
        this.coocon = coocon;

        coocon.callCooconApiToken();   //kkk
    }


//    @Scheduled(fixedDelay=10000) // 이전 task 종료 시간으로부터 한시간 후 마다 동작
//    @Scheduled(fixedRate = 10000) // 이전 task 시작 시간으로부터 매 1초마다 동작
    @Scheduled(cron = "0 0 0 15 * *")  // 매월 15일 00시에 동작
    public void callCooconApiToken() {
        Utils.logCalled("callCooconApiToken", coocon.COOCON_AUTH);

        coocon.callCooconApiToken();
        Utils.logCallReturned("success", coocon.COOCON_AUTH);
    }

}
