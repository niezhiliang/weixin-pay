package com.niezhiliang.wei.pay.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.niezhiliang.wei.pay.entity.QrcodeBo;
import com.niezhiliang.wei.pay.entity.QrcodeRep;
import com.niezhiliang.wei.pay.entity.RefundBo;
import com.niezhiliang.wei.pay.entity.RefundRep;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public interface PayMethods {
    //获取二维码方法
    QrcodeRep getQrcode(QrcodeBo qrcodeBo);

    //支付回调
    WxPayOrderNotifyResult payNotify(String xmlData);

    //退款
    RefundRep refund(RefundBo refundBo);

}
