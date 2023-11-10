package com.example.payment.service.impl;

import com.example.payment.service.Available;
import com.example.payment.service.payAble;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class aliPay implements payAble, Available {
    @Value("${aliUrl}")
    private String u;
    public String Token;
    public String orderId;
    @Override
    public Boolean isAvailable(){
        OkHttpClient client = new OkHttpClient();
        String url = u+""+"isRunning";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            Token = jsonNode.get("data").get("token").asText();
            System.out.println(Token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (Token.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public void pay(String mchId, String appId, double amount){
        OkHttpClient client = new OkHttpClient();
        String url= u+""+"transaction/pay";
        RequestBody formBody = new FormBody.Builder()
                .add("amount", String.valueOf(amount))
                .add("appid", appId)
                .add("content","缴费 ")
                .add("mchid",mchId)
                .add("token",Token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            System.out.println(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonNode.get("data").get("orderid").asText());
            orderId=jsonNode.get("data").get("orderid").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public String query(String orderid){
        String jsonString;
        OkHttpClient client = new OkHttpClient();
        String url= u+""+"order/query";
        RequestBody formBody = new FormBody.Builder()
                .add("orderID", orderId)
                .add("token",Token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            jsonString =response.body().string();
            System.out.println(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }
}
