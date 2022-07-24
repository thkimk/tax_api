package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.model.Coocon;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.dto.CcAuthorizeDto;
import com.hanwha.tax.apiserver.entity.CustInfo;
import com.hanwha.tax.apiserver.entity.TotalIncome;
import com.hanwha.tax.apiserver.entity.TotalOutgoing;
import com.hanwha.tax.apiserver.repository.*;
import com.hanwha.tax.apiserver.vo.*;
import com.hanwha.tax.apiserver.dto.CcExpenseDto;
import com.hanwha.tax.apiserver.dto.CcIncomeDto;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MydataService {

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    TotalIncomeRepository totalIncomeRepository;

    @Autowired
    TotalOutgoingRepository totalOutgoingRepository;

    @Autowired
    CustInfoRepository custInfoRepository;

    private final Coocon coocon;

    public CcIncomeDto ccIncome() {
        // 쿠콘 API 호출
        String ci = authInfoRepository.selectCiByCid(Utils.cid());
        CcIncomeVo ccIncomeVo = new CcIncomeVo(ci);

        Utils.logExtCall("ccIncome", ccIncomeVo);
        CcIncomeDto ccIncomeDto = (CcIncomeDto)coocon.callCooconApi("/apis/user/hw/bank/income", ccIncomeVo, CcIncomeDto.class);

        return ccIncomeDto;
    }

/*
    public IncomeDto mydataIncome2(IncomeVo incomeVo) {
        // 쿠콘 API 호출
        CooconIncome cooconIncome = new CooconIncome(incomeVo.getCi());

//        IncomeDto incomeDto = (IncomeDto)callCooconApi("/apis/user/hw/bank/income", cooconIncome);
        IncomeDto incomeDto = null;
        {
            String url = "/apis/user/hw/bank/income";
            Object obj = cooconIncome;

            HttpHeaders httpHeaders = new HttpHeaders();
            String transId = "hanwha_".concat(Utils.random10value());

            httpHeaders.add("x-api-tran-id", transId);
            httpHeaders.add("Authorization", "Bearer ".concat(COOCON_AUTH));

            HttpEntity entity = new HttpEntity(obj, httpHeaders);
            incomeDto = restTemplate.postForObject(cooconUrl.concat(url), entity, IncomeDto.class);
        }

        return incomeDto;
    }
*/


    public CcExpenseDto ccExpense() {
        String ci = authInfoRepository.selectCiByCid(Utils.cid());
        CcExpenseVo ccExpenseVo = new CcExpenseVo(ci);

        // 쿠콘 API 호출
        Utils.logExtCall("ccExpense", ccExpenseVo);
        CcExpenseDto ccExpenseDto = coocon.callCooconApi("/apis/user/hw/card/expense", ccExpenseVo, CcExpenseDto.class);

        return ccExpenseDto;
    }


    public CcAuthorizeDto ccAuthorize() {
        String cid = Utils.cid();
        String ci = "Gg3GIzkmziVhqfx8IOSFItnLjUP49iIM";//kkkauthInfoRepository.selectCiByCid(cid);
        CustInfo custInfo = custInfoRepository.findByCid(cid);
        if (custInfo == null) {
            return null;
        }

        CcAuthorizeVo ccAuthorizeVo = new CcAuthorizeVo(ci, custInfo);

        // 쿠콘 API 호출
        Utils.logExtCall("ccAuthorize", ccAuthorizeVo);
        CcAuthorizeDto ccAuthorizeDto = coocon.callCooconApi("/apis/user/authorize", ccAuthorizeVo, CcAuthorizeDto.class);

        return ccAuthorizeDto;
    }


    public List<TotalIncome> totalIncome(Integer year, Integer month) {
        List<TotalIncome> totalIncomes = null;

        if (year != null) {
            if (month != null) {
                totalIncomes = totalIncomeRepository.findByYearAndMonth(year, month);
            } else {
                totalIncomes = totalIncomeRepository.findByYearOrderByMonthAsc(year);

                int size = totalIncomes.size();
                if (size < 12) {
                    for (int i=1; i<=12; i++) {
                        boolean exists = false;
                        for (TotalIncome tmp : totalIncomes) {
                            if (tmp.getMonth() == i) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            TotalIncome tmp = new TotalIncome(year, i, Long.valueOf(0));
                            totalIncomes.add(tmp);
                        }
                    }
                }
            }
        } else {
            totalIncomes = totalIncomeRepository.findAll();
        }

/*
        String cid = Utils.cid();
        if (incomeVo.getYear() != null) {
            if (incomeVo.getMonth() != null) {
                totalIncomes = totalIncomeRepository.findByCustIdAndYearAndMonth(cid, incomeVo.getYear(), incomeVo.getMonth());
            } else {
                totalIncomes = totalIncomeRepository.findByCustIdAndYear(cid, incomeVo.getYear());
            }
        } else {
            totalIncomes = totalIncomeRepository.findByCustId(cid);
        }
*/

        return totalIncomes;
    }


    public List<TotalOutgoing> totalOutgoing(Integer year, Integer month) {
        List<TotalOutgoing> totalOutgoings = null;

        if (year != null) {
            if (month != null) {
                totalOutgoings = totalOutgoingRepository.findByYearAndMonth(year, month);
            } else {
                totalOutgoings = totalOutgoingRepository.findByYearOrderByMonthAsc(year);

                int size = totalOutgoings.size();
                if (size < 12) {
                    for (int i=1; i<=12; i++) {
                        boolean exists = false;
                        for (TotalOutgoing tmp : totalOutgoings) {
                            if (tmp.getMonth() == i) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            TotalOutgoing tmp = new TotalOutgoing(year, i, Long.valueOf(0));
                            totalOutgoings.add(tmp);
                        }
                    }
                }
            }
        } else {
            totalOutgoings = totalOutgoingRepository.findAll();
        }

/*
        String cid = Utils.cid();
        if (outgoingVo.getYear() != null) {
            if (outgoingVo.getMonth() != null) {
                totalOutgoings = totalOutgoingRepository.findByCustIdAndYearAndMonth(cid, outgoingVo.getYear(), outgoingVo.getMonth());
            } else {
                totalOutgoings = totalOutgoingRepository.findByCustIdAndYear(cid, outgoingVo.getYear());
            }
        } else {
            totalOutgoings = totalOutgoingRepository.findByCustId(cid);
        }
*/

        return totalOutgoings;
    }


    // Job
    public void batchDataJob() {
        String down_path = "D:/dev/hanwha/nas/tax/down/";
        String mydata_path = "D:/dev/hanwha/nas/tax/mydata/";
        String yyyymmdd = Utils.yyyymmddYester(); //yyyymmdd = "20220705";//kkk

        // SFTP Get 수행 (/nas/tax/down)
        mydataSftpGet(down_path);
        
        // zip 압축 해제 (/nas/tax/mydata/yyyymmdd/*)
        mydata_path = mydata_path.concat("/".concat(yyyymmdd))+ "/";
        mydataUnzip(down_path.concat(yyyymmdd.concat("_IS000001_01_BANK_TRANS.zip")), mydata_path);
        mydataUnzip(down_path.concat(yyyymmdd.concat("_IS000001_01_CARD_APPR.zip")), mydata_path);

        // File load (parsing)
        // DB Upsert (mydata_income)
        mydataIncomeLoad(mydata_path.concat(yyyymmdd+ "_IS000001_01_BANK_TRANS"));
        mydataOutgoingLoad(mydata_path.concat(yyyymmdd+ "_IS000001_01_CARD_APPR"));

    }


    private void mydataSftpGet(String downPath) {
        String user = "IS000001";
        String pwd = "d1OryUJJr3";
        String host = "183.111.160.204";
        int port = 3300;
        String yyyymmdd = Utils.yyyymmddYester(); //yyyymmdd = "20220705";//kkk
        String sourceFile1 = "/data/".concat(yyyymmdd)+ "/"+ yyyymmdd+ "_IS000001_01_BANK_TRANS.zip";
        String sourceFile2 = "/data/".concat(yyyymmdd)+ "/"+ yyyymmdd+ "_IS000001_01_CARD_APPR.zip";
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        // sftp 세션 생성
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(pwd);
            session.setConfig(config);
            session.connect(3000);
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // down bank
            FileOutputStream fo1 = new FileOutputStream(new File(downPath.concat(yyyymmdd.concat("_IS000001_01_BANK_TRANS.zip"))));
            channelSftp.get(sourceFile1, fo1);

            // down card
            FileOutputStream fo2 = new FileOutputStream(new File(downPath.concat(yyyymmdd.concat("_IS000001_01_CARD_APPR.zip"))));
            channelSftp.get(sourceFile2, fo2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channelSftp.disconnect();
            session.disconnect();
        }
    }

    private void mydataIncomeLoad(String source_file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source_file));

            String str;
            boolean isIng = false;
            while ((str = reader.readLine()) != null) {
                String[] vals = str.split("|");
                if (vals == null) break;
                if (vals[0].equals("ST")) {
                    isIng = true;
                } else if (vals[0].equals("ED")) {
                    break;
                } else {
                    // 본처리
                    if (isIng) {
                        String[] data = str.split("|");
                        if (data == null) break;

                        System.out.println("## mydataIncomeLoad : "+ str);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mydataOutgoingLoad(String source_file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source_file));

            String str;
            boolean isIng = false;
            while ((str = reader.readLine()) != null) {
                String[] vals = str.split("|");
                if (vals == null) break;
                if (vals[0].equals("ST")) {
                    isIng = true;
                } else if (vals[0].equals("ED")) {
                    break;
                } else {
                    // 본처리
                    if (isIng) {
                        String[] data = str.split("|");
                        if (data == null) break;

                        System.out.println("## mydataOutgoingLoad : "+ str);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    private void mydataUnzip(String source_file, String target_path) {
        File zipFile = new File(source_file);
        checkPath(target_path);

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(zipFile))) {
            try (ZipInputStream zipInputStream = new ZipInputStream(in)) {
                ZipEntry zipEntry = null;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    int len = 0;
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target_path+ zipEntry.getName()))) {
                        while ((len = zipInputStream.read()) != -1) {
                            out.write(len);
                        }

                        zipInputStream.closeEntry();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

