package com.example.payment;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PayMentApplicationTests {


    @Value("${url}")
    String u;

    String Token;
    @Test
    public void isAvailable() {
        OkHttpClient client = new OkHttpClient();
        String url = u+""+"isRunning";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            Token = jsonNode.get("data").get("token").asText();
//            Token = jsonNode1.get("token").asText();
            System.out.println(Token);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Value("${mchid}")
    private String mchId;
    @Value("${appid}")
    private String appId;
    @Value("${token}")
    private String token;
    @Test
    public void payTest(){
        OkHttpClient client = new OkHttpClient();
        String url= u+""+"transaction/pay";
        RequestBody formBody = new FormBody.Builder()
                .add("amount", "100")
                .add("appid", appId)
                .add("content","缴费 ")
                .add("mchid",mchId)
                .add("token",token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            System.out.println(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonNode.get("data").get("orderid").asText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Value("${orderid}")
    private String orderid;
    @Test
    public void queryTest(){
        String jsonString;
        OkHttpClient client = new OkHttpClient();
        String url= u+""+"order/query";
        RequestBody formBody = new FormBody.Builder()
                .add("orderID", "293ea07837954654bdfc8c8f7583de1d")
                .add("token","13577ce5-2dea-42b9-9fe6-5a991446cf1e")
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonString);
            System.out.println(jsonNode.get("code").asText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void refundsTest(){
        String jsonString;
        String url = u + "" + "/transaction/refunds";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("amount","10")
                .add("appid",appId)
                .add("content","退款")
                .add("mchid",mchId)
                .add("token",token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void queryRefunds(){
        String jsonString;
        String url = u + "" + "order/queryRefunds";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new  FormBody.Builder()
                .add("orderID","c5725c1f-a15c-4bb6-a1a4-d30d0866a515")
                .add("token", token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try (Response response = client.newCall(request).execute()){
            System.out.println(response.body().string());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void isAvailablea() {
        OkHttpClient client = new OkHttpClient();
        String url = u+""+"isRunning";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String jsonString =response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            Token = jsonNode.get("data").get("token").asText();
//            Token = jsonNode1.get("token").asText();
            System.out.println(Token);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}