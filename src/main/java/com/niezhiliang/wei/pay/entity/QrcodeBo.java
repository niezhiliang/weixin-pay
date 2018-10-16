package com.niezhiliang.wei.pay.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@XStreamAlias("xml")
public class QrcodeBo extends BaseBo {

    //商品描述
    private String body;

    //商户订单号
    private String out_trade_no;

    //标价金额
    private String total_fee;

    //终端IP
    private String spbill_create_ip;

    //交易类型
    private String trade_type;

    //通知地址
    private String notify_url;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
