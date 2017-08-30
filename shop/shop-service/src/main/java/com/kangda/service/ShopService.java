package com.kangda.service;

import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.dao.ShopDao;
import com.kangda.entity.Shop;
import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop findById(Integer id) {
        return shopDao.findById(id);
    }
}
