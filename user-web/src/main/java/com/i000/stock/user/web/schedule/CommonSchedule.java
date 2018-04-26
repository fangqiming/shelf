package com.i000.stock.user.web.schedule;

import com.i000.stock.user.api.service.CompanyService;
import com.i000.stock.user.api.service.IndexService;
import com.i000.stock.user.api.service.PriceService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:33 2018/4/25
 * @Modified By:
 */
@EnableScheduling
@Component
public class CommonSchedule {

    @Resource
    private CompanyService companyService;

    @Resource
    private IndexService indexService;

    @Resource
    private PriceService priceService;

    @Scheduled(cron = "0 15 2 * * ?")
    public void updateCompany() {
        companyService.updateInfo();
    }

    @Scheduled(cron = "0 10 15 * * ?")
    public void updatePrice() {
        priceService.save();
    }

    @Scheduled(cron = "0 15 15 * * ?")
    public void updateIndex() {
        indexService.save();
    }
}
