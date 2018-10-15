package com.niezhiliang.wei.pay.entity;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
@XStreamAlias("xml")
public class QrcodeRep extends BaseWxPayResult {

    @XStreamAlias("prepay_id")
    private String prepay_id;

    @XStreamAlias("trade_type")
    private String trade_type;

    @XStreamAlias("code_url")
    private String code_url;


    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
