package com.niezhiliang.wei.pay.serviceImpl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.util.SignUtils;
import com.niezhiliang.wei.pay.entity.*;
import com.niezhiliang.wei.pay.enums.APIURLENUMS;
import com.niezhiliang.wei.pay.service.PayMethods;
import com.niezhiliang.wei.pay.utils.HttpUtil;
import com.niezhiliang.wei.pay.utils.UuidUtils;
import com.niezhiliang.wei.pay.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;

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
    @Value("${wx.pay.pay_notify}")
    private String pay_notify;
    @Value("${wx.pay.trade_type}")
    private String trade_type;
    @Value("${wx.pay.refund_notify}")
    private String refund_notify;
    private String [] params = {"appid","mch_id","trade_type"};
    private final static Logger logger = LoggerFactory.getLogger(PayMethodsImpl.class);

    /**
     * 统一下单（二维码）
     * @param qrcodeBo
     * @return
     */
    public QrcodeRep getQrcode(QrcodeBo qrcodeBo) {
        qrcodeBo = (QrcodeBo) fillParamsToObj(qrcodeBo);
        qrcodeBo.setTotal_fee(new BigDecimal(qrcodeBo.getTotal_fee()).multiply(new BigDecimal("100")).toString());
        qrcodeBo.setNotify_url(pay_notify);
        qrcodeBo.setSign(null);
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

    /**
     * 支付回调
     * @param xmlData
     * @return
     */
    public WxPayOrderNotifyResult payNotify(String xmlData) {
        logger.info("微信支付异步通知请求参数：{}", xmlData);
        return WxPayOrderNotifyResult.fromXML(xmlData,WxPayOrderNotifyResult.class);
    }

    /**
     * 关闭订单
     * @param closeOrderBo
     * @return
     */
    @Override
    public QrcodeRep closeOrder(CloseOrderBo closeOrderBo) {
        logger.info("微信关闭订单号：{}", closeOrderBo.getOut_trade_no());
        closeOrderBo = (CloseOrderBo) fillParamsToObj(closeOrderBo);
        closeOrderBo.setSign(SignUtils.createSign(closeOrderBo, "MD5", mchKey, new String[0]));
        String xml = XmlUtils.toXML(closeOrderBo);
        String responseContent = null;
        try {
            responseContent = HttpUtil.doPost(APIURLENUMS.API_URL_CLOSE_ORDER.getUrl(),xml);
            logger.info("微信关闭订单返回参数：{}", responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return QrcodeRep.fromXML(responseContent,QrcodeRep.class);
    }


    /**
     * 退款申请
     * @param refundBo
     * @return
     */
    public WxPayRefundResult refund(RefundBo refundBo) {
        logger.info("微信退款商户订单号：{},微信退款商户退款单号", refundBo.getOut_trade_no(),refundBo.getOut_refund_no());
        refundBo = (RefundBo) fillParamsToObj(refundBo);
        refundBo.setNotify_url(refund_notify);
        //微信支付提交的金额是不能带小数点的，且是以分为单位，所以我们系统如果是以元为单位要处理下金额，即先乘以100，再去小数点
        refundBo.setRefund_fee(new BigDecimal(refundBo.getRefund_fee()).multiply(new BigDecimal("100")).toString());
        refundBo.setTotal_fee(new BigDecimal(refundBo.getTotal_fee()).multiply(new BigDecimal("100")).toString());
        refundBo.setSign(SignUtils.createSign(refundBo, "MD5", mchKey, new String[0]));
        String xml = XmlUtils.toXML(refundBo);
        String responseContent = null;
        try {
            responseContent = HttpUtil.doRefund(APIURLENUMS.API_URL_REFUND.getUrl(),xml);
            logger.info("微信退款申请返回参数：{}", responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WxPayRefundResult.fromXML(responseContent,WxPayRefundResult.class);
    }

    /**
     * 退款查询
     * @param refundQueryBo
     * @return
     */
    @Override
    public WxPayRefundQueryResult refundQuery(RefundQueryBo refundQueryBo) {
        logger.info("微信退款查询订单号：{}", refundQueryBo.getOut_trade_no());
        refundQueryBo = (RefundQueryBo) fillParamsToObj(refundQueryBo);
        refundQueryBo.setSign(SignUtils.createSign(refundQueryBo, "MD5", mchKey, new String[0]));
        String xml = XmlUtils.toXML(refundQueryBo);
        String responseContent = null;
        try {
            responseContent = HttpUtil.doPost(APIURLENUMS.API_URL_REFUND_QUERY.getUrl(),xml);
            logger.info("微信退款查询返回参数：{}", responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WxPayRefundQueryResult.fromXML(responseContent,WxPayRefundQueryResult.class);
    }

    /**
     * 退款回调
     * @param xmlData
     * @return
     */
    @Override
    public WxPayRefundNotifyResult refundNotify(String xmlData) {
        logger.info("微信退款回调请求参数：{}", xmlData);
        try {
            return   WxPayRefundNotifyResult.fromXML(xmlData,mchKey);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 利用反射填充部分配置必填参数
      * @param object
     * @return
     */
    public  Object fillParamsToObj(Object object) {
        Class clz = object.getClass();
        for (int i = 0;i < params.length; i++) {
            Field field = null;
            try {
                try {
                    field = clz.getSuperclass().getDeclaredField(params[i]);
                } catch (Exception e) {
                    field = clz.getDeclaredField(params[i]);
                }
                field.setAccessible(true);
                field.set(object, this.getClass().getDeclaredField(params[i]).get(this));
                if (i == 0) {
                    field = clz.getSuperclass().getDeclaredField("nonce_str");
                    field.setAccessible(true);
                    field.set(object,UuidUtils.getRandUUID());
                }
            } catch (Exception e) {
                logger.warn(String.format("实体类填充发生异常，字段：%s",params[i]));
            }
        }
        return object;
    }

}
