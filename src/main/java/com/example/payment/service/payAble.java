package com.example.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface payAble {
    void pay(String mchid,String appid, double amount);
    String query(String orderid);
}
