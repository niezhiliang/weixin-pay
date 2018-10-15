package com.niezhiliang.wei.pay.entity;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@XStreamAlias("xml")
public class RefundRep extends BaseWxPayResult {
     @XStreamAlias("transaction_id")
     private String transaction_id;

     @XStreamAlias("out_trade_no")
     private String out_trade_no;

     @XStreamAlias("out_refund_no")
     private String out_refund_no;

     @XStreamAlias("refund_id")
     private String refund_id;

     @XStreamAlias("refund_fee")
     private String refund_fee;

     @XStreamAlias("total_fee")
     private String total_fee;

     @XStreamAlias("cash_fee")
     private String cash_fee;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

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

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }
}
