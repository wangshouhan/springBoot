package com.kangda.controller;

import com.kangda.api.ISendService;
import com.kangda.api.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 * <p>
 * 商品详情
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    /**
     * 对外暴露的接口shop详情
     */
    @RequestMapping("info")
    public Map<String, Object> detail() {
        return shopService.findByUserId();
    }
}
