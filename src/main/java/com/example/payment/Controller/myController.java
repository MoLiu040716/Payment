package com.example.payment.Controller;

import com.example.payment.Client;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class myController {
    @Resource
    private Client client;
    @Value("${url}")
    private String u;
    @Value("${mchid}")
    private String mchId;
    @Value("${appid}")
    private String appId;
    @Value("${orderid}")
    private String orderid;


    @GetMapping("Available")
    public Boolean isAvailable(){
        client.isAvailable_wechat();
        return true;
    }
    @GetMapping("/Pay")
    public String pay(){
        client.pay_wechat(mchId,appId,10);
        return "ok";
    }

    @GetMapping("/refund")
    public void refund(){
        client.queryRefunds_wechat("s");
        System.out.println(1);
    }
}
