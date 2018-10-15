package com.niezhiliang.wei.pay.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.niezhiliang.wei.pay.entity.QrcodeBo;
import com.niezhiliang.wei.pay.entity.RefundBo;
import com.niezhiliang.wei.pay.service.PayMethods;
import com.niezhiliang.wei.pay.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@RestController
@RequestMapping(value = "pay")
public class WxPayController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PayMethods payMethods;

    /**
     * 	"body":"测试支付",
     * 	"out_trade_no":"11111111",
     * 	"total_fee":"1",
     * @param qrcodeBo
     * @return
     */
    @RequestMapping(value = "qrcode")
    public String getQrcodeStr(@RequestBody QrcodeBo qrcodeBo) {
        System.out.println(qrcodeBo.getOut_trade_no());
        qrcodeBo.setSpbill_create_ip(IpUtils.getIpAddress(request));
        return  JSON.toJSONString(payMethods.getQrcode(qrcodeBo));
    }

    /**
     * 支付扫码回调
     * @param xmlData
     * @return
     */
    @RequestMapping(value = "notify_url")
    public String notifyMethod(@RequestBody String xmlData) {
        WxPayOrderNotifyResult wxPayOrderNotifyResult =
                payMethods.payNotify(xmlData);
        if (wxPayOrderNotifyResult.getReturnCode().equals("SUCCESS")
                &&wxPayOrderNotifyResult.getResultCode().equals("SUCCESS")) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    /**
     * 退款接口
     * @param refundBo
     * @return
     */
    @RequestMapping(value = "refund")
    public String refund(@RequestBody RefundBo refundBo) {

        return JSON.toJSONString(payMethods.refund(refundBo));
    }
}
