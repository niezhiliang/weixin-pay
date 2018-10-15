package com.niezhiliang.wei.pay.serviceImpl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.util.SignUtils;
import com.niezhiliang.wei.pay.APIURLENUMS;
import com.niezhiliang.wei.pay.entity.QrcodeBo;
import com.niezhiliang.wei.pay.entity.QrcodeRep;
import com.niezhiliang.wei.pay.entity.RefundBo;
import com.niezhiliang.wei.pay.entity.RefundRep;
import com.niezhiliang.wei.pay.service.PayMethods;
import com.niezhiliang.wei.pay.utils.HttpUtil;
import com.niezhiliang.wei.pay.utils.UuidUtils;
import com.niezhiliang.wei.pay.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@Service
public class PayMethodsImpl implements PayMethods {
    @Value("${wx.pay.appId}")
    private String appid;
    @Value("${wx.pay.mchId}")
    private String mch_id;
    @Value("${wx.pay.mchKey}")
    private String mchKey;
    @Value("${wx.pay.notify_url}")
    private String notify_url;
    @Value("${wx.pay.trade_type}")
    private String trade_type;
    private String [] params = {"appid","mch_id","notify_url","trade_type"};
    private final static Logger logger = LoggerFactory.getLogger(PayMethodsImpl.class);

    public QrcodeRep getQrcode(QrcodeBo qrcodeBo) {
        qrcodeBo = (QrcodeBo) fillParamsToObj(qrcodeBo);
        qrcodeBo.setSign(null);
        qrcodeBo.setNonce_str(UuidUtils.getRandUUID());
        qrcodeBo.setSign(SignUtils.createSign(qrcodeBo, "MD5", mchKey, new String[0]));
        String xml = XmlUtils.toXML(qrcodeBo);
        logger.info("微信二维码下单请求参数：{}", xml);
        String responseContent = null;
        try {
            responseContent = HttpUtil.doPost(APIURLENUMS.API_URL_QRCODE.getUrl(),xml);
            logger.info("微信二维码下单返回参数：{}", responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return QrcodeRep.fromXML(responseContent,QrcodeRep.class);
    }

    public WxPayOrderNotifyResult payNotify(String xmlData) {
        logger.info("微信支付异步通知请求参数：{}", xmlData);
        WxPayOrderNotifyResult wxPayOrderNotifyResult =
                WxPayOrderNotifyResult.fromXML(xmlData,WxPayOrderNotifyResult.class);
        return wxPayOrderNotifyResult;
    }

    public RefundRep refund(RefundBo refundBo) {
        logger.info("微信退款商户订单号：{},微信退款商户退款单号", refundBo.getOut_trade_no(),refundBo.getOut_refund_no());
        refundBo.setSign(SignUtils.createSign(refundBo, "MD5", mchKey, new String[0]));
        String xml = XmlUtils.toXML(refundBo);
        String responseContent = null;
        try {
            responseContent = HttpUtil.doPost(APIURLENUMS.API_URL_REFUND.getUrl(),xml);
            logger.info("微信退款申请返回参数：{}", responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RefundRep.fromXML(responseContent,RefundRep.class);
    }


    /**
     * 利用反射填充部分配置必填参数
      * @param object
     * @return
     */
    public  Object fillParamsToObj(Object object) {
        for (int i = 0;i < params.length; i++) {
            try {
                Field field = object.getClass().getDeclaredField(params[i]);
                field.setAccessible(true);
                field.set(object, this.getClass().getDeclaredField(params[i]).get(this));
            } catch (Exception e) {
                logger.error(String.format("实体类填充发生异常，字段：%s",params[i]));
            }
        }
        return object;
    }

}
