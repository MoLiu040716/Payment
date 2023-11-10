package com.example.payment;

import com.example.payment.service.Available;
import com.example.payment.service.payAble;
import com.example.payment.service.refundAble;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Client {
    @Resource
    @Qualifier("aliPay")
    private payAble aliPay;
    @Resource
    @Qualifier("aliRefund")
    private refundAble aliRefunds;

    @Resource
    @Qualifier("wechatPay")
    private payAble wechatPay;
    @Resource
    @Qualifier("wechatRefund")
    private refundAble wechatRefunds;

    @Resource
    private Available available;

   public void isAvailable_ali(){
       available.isAvailable();
   }
   public void pay_ali(String mchid, String appid, double amount){
        aliPay.pay( mchid, appid,  amount);
    }
   public void query_ali(String orderid){
        aliPay.query(orderid);
    }
   public void refunds_ali(String mchid, String appid, double amount){
        aliRefunds.refunds(mchid,appid,amount);
    }
    public void queryRefunds_ali(String refundid){
        aliRefunds.queryRefunds(refundid);
    }

    public void isAvailable_wechat(){
        available.isAvailable();
    }
    public void pay_wechat(String mchid, String appid, double amount){
        wechatPay.pay( mchid, appid,  amount);
    }
    public void query_wechat(String orderid){
        wechatPay.query(orderid);
    }
    public void refunds_wechat(String mchid, String appid, double amount){
        wechatRefunds.refunds(mchid,appid,amount);
    }
    public void queryRefunds_wechat(String refundid){
        wechatRefunds.queryRefunds(refundid);
    }
}
