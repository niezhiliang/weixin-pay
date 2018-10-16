package com.niezhiliang.wei.pay.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.niezhiliang.wei.pay.entity.*;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public interface PayMethods {
    //获取二维码方法
    QrcodeRep getQrcode(QrcodeBo qrcodeBo);

    //订单关闭
    QrcodeRep closeOrder(CloseOrderBo closeOrderBo);

    //支付回调
    WxPayOrderNotifyResult payNotify(String xmlData);

    //退款
    WxPayRefundResult refund(RefundBo refundBo);

    //退款查询
    WxPayRefundQueryResult refundQuery(RefundQueryBo refundBo);

    //退款回调
    WxPayRefundNotifyResult refundNotify(String xmlData);

}
