package com.atguigu.gmall.sms.api;


import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.sms.dto.SkuSaleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface GmallSmsApi {
    @PostMapping("/sms/skubounds/skusale/save")
    public Resp<Object> saveSkuSaleInfo(@RequestBody SkuSaleDTO skuSaleDTO);

}
