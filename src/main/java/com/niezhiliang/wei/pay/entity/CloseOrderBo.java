package com.niezhiliang.wei.pay.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@XStreamAlias("xml")
public class CloseOrderBo extends BaseBo{
    //商户订单号
    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
