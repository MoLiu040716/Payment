package com.example.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface refundAble {
    void refunds(String mchid,String appid, double amount);
    String queryRefunds(String refundid);
}
