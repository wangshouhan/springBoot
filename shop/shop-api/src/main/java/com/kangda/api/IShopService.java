package com.kangda.api;

import com.kangda.entity.Shop;

/**
 * Created by shouhan on 2017/8/22.
 */
public interface IShopService {
    Shop findById(Integer id);
}
