package com.niezhiliang.wei.pay.controller;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.niezhiliang.wei.pay.entity.CloseOrderBo;
import com.niezhiliang.wei.pay.entity.QrcodeBo;
import com.niezhiliang.wei.pay.entity.RefundBo;
import com.niezhiliang.wei.pay.entity.RefundQueryBo;
import com.niezhiliang.wei.pay.service.PayMethods;
import com.niezhiliang.wei.pay.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
     * 订单关闭
     * "out_trade_no":"11111111",
     * @param closeOrderBo
     * @return
     */
    @RequestMapping(value = "close_order")
    public String closeOrder(@RequestBody CloseOrderBo closeOrderBo) {

        return JSON.toJSONString(payMethods.closeOrder(closeOrderBo));
    }

    /**
     * 支付扫码回调
     * @return
     */
    @RequestMapping(value = "pay_notify")
    public String notifyMethod() {
        String xmlData = getRequestXml();
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
     * {
     * 	"out_refund_no":"159006101431",
     * 	"out_trade_no":"11111111",
     * 	"total_fee":0.01,
     * 	"refund_fee":0.01
     * }
     * @return
     */
    @RequestMapping(value = "refund")
    public String refund(@RequestBody RefundBo refundBo) {

        return JSON.toJSONString(payMethods.refund(refundBo));
    }

    /**
     * 退款回调
     * @return
     */
    @RequestMapping(value = "refund_notify")
    public String refund_notify() {
        String xmlData = getRequestXml();
        WxPayRefundNotifyResult wxPayRefundNotifyResult =
                payMethods.refundNotify(xmlData);
        if (wxPayRefundNotifyResult.getReturnCode().equals("SUCCESS")
                &&wxPayRefundNotifyResult.getResultCode().equals("SUCCESS")) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    /**
     * 退款查询
     * @param refundQueryBo
     * "out_trade_no":"11111111",
     * @return
     */
    @RequestMapping(value = "refund_query")
    public String refundQuery(@RequestBody RefundQueryBo refundQueryBo) {

        return JSON.toJSONString(payMethods.refundQuery(refundQueryBo));
    }

    /**
     * 获取支付和退款回调参数
     * @return
     */
    private String getRequestXml() {
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            return  new String(outSteam.toByteArray(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
