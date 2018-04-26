package com.i000.stock.user.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.i000.stock.user.api.service.CompanyService;
import com.i000.stock.user.dao.mapper.CompanyMapper;
import com.i000.stock.user.dao.model.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:04 2018/4/25
 * @Modified By:
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    private void save() throws IOException {
        Document doc = Jsoup.connect("http://quote.eastmoney.com/stocklist.html").get();
        Elements sltit = doc.getElementsByClass("sltit");
        for (Element element : sltit) {
            String prefix = element.child(0).attr("name");
            Element companys = element.nextElementSibling();
            Elements ul = companys.children();
            for (Element li : ul) {
                String info = li.text();
                String code = getCode(info);
                if (code.startsWith("60") || code.startsWith("000") || code.startsWith("002")) {
                    Company company = Company.builder().prefix(prefix).code(code).name(getName(info)).build();
                    companyMapper.insert(company);
                }
            }
        }
    }

    private void clear() {
        companyMapper.truncate();
    }

    private String getName(String str) {
        if (!StringUtils.isEmpty(str)) {
            try {
                return str.split("\\(")[0];
            } catch (Exception e) {
            }
        }
        return "";
    }


    private String getCode(String str) {
        if (!StringUtils.isEmpty(str)) {
            try {
                return str.split("\\(")[1].split("\\)")[0];
            } catch (Exception e) {
            }
        }
        return "";
    }

    @Override
    public Boolean updateInfo() {
        clear();
        try {
            save();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
