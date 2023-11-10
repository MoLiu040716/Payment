package com.example.payment.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WechatRefund implements com.example.payment.service.refundAble {
    @Value("${url}")
    private String u;
    @Resource
    private WechatPay wechatPay;
    @Override
    public void refunds(String mchid, String appid, double amount){
        String url = u + "" + "/transaction/refunds";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("amount","10")
                .add("appid",appid)
                .add("content","退款")
                .add("mchid",mchid)
                .add("token", wechatPay.Token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String queryRefunds(String refundid){
        String url = u + "" + "order/queryRefunds";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new  FormBody.Builder()
                .add("orderID", wechatPay.orderId)
                .add("token", wechatPay.Token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()){
            String jsonString = response.body().string();
            System.out.println(jsonString);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
