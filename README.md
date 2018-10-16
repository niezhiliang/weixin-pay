微信支付提交的金额是不能带小数点的，且是以分为单位，所以我们系统如果是以元为单位要处理下金额，即先乘以100，再去小数点

### 二维码生成接口

##### 描述: 生成微信支付所需的二维码链接地址(通过这个地址用插件生产二维码图片扫描进行支付)

##### Request URL:http://127.0.0.1:8011/pay/qrcode

##### Request Method: POST(content-Type=application/json)

#### 参数：

| 名称   | 类型 | 是否必须| 参数描述
| :----: | :---: | :---: | :---:
| body  |String|  必须  |   订单描述
| out_trade_no  |String|  必须  |   交易流水号（不可重复，重复就会显示二维码失效）
| total_fee  |String|  必须  |   金额

这里只是填了一些必要的参数，更多请求参数详情访问微信的开发文档

https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1


### 关闭订单接口

##### 描述: 由于某些原因我们可以进行订单进行关闭，让其失效

##### Request URL: http://127.0.0.1:8011/pay/close_order

##### Request Method: POST(content-Type=application/json)

#### 参数：

| 名称   | 类型 | 是否必须| 参数描述
| :----: | :---: | :---: | :---:
| out_trade_no  |String|  必须  |   交易流水号

这里只是填了一些必要的参数，更多请求参数详情访问微信的开发文档

https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_3


### 退款接口

##### 描述: 由于某些原因我们可以进行退款

##### Request URL: http://127.0.0.1:8011/pay/refund

##### Request Method: POST

#### 参数：

| 名称   | 类型 | 是否必须| 参数描述
| :----: | :---: | :---: | :---:
| out_trade_no  |String|  必须  |   交易流水号
| out_refund_no  |String|  必须  |   金额（随机数 不会重复就行）
| total_fee  |String|  必须  |   支付订单金额
| refund_fee  |String|  必须  |   退款金额

这里只是填了一些必要的参数，更多请求参数详情访问微信的开发文档

https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4


### 退款结果查询接口

##### 描述: 由于某些原因我们可以进行退款

##### Request URL: http://127.0.0.1:8011/pay/refund_query

##### Request Method: POST(content-Type=application/json)

#### 参数：

| 名称   | 类型 | 是否必须| 参数描述
| :----: | :---: | :---: | :---:
| out_trade_no  |String|  必须  |   交易流水号

这里只是填了一些必要的参数，更多请求参数详情访问微信的开发文档

https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_5





