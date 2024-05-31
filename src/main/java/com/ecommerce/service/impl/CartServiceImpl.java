package com.ecommerce.service.impl;

import com.ecommerce.entity.CartEntity;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    public void createCart() {

    }
}



