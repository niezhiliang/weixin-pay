package com.niezhiliang.wei.pay.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@XStreamAlias("xml")
public class RefundBo {
    //公众账号ID
    private String appid;

    //商户号
    private String mch_id;

    //随机字符串
    private String nonce_str;

    //签名
    private String sign;

    //商户订单号
    private String out_trade_no;

    //out_refund_no
    private String out_refund_no;

    //订单总金额
    private String total_fee;

    //退款总金额
    private String refund_fee;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
