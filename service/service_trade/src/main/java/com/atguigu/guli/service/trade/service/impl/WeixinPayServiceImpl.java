package com.atguigu.guli.service.trade.service.impl;

import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import com.atguigu.guli.common.base.util.HttpClientUtils;
import com.atguigu.guli.service.base.exception.GuliException;
import com.atguigu.guli.service.trade.entity.Order;
import com.atguigu.guli.service.trade.service.OrderService;
import com.atguigu.guli.service.trade.service.WeixinPayService;
import com.atguigu.guli.service.trade.util.WeiXinPayProperties;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wind
 * @create 2020-08-02 17:06
 */
@Service
@Slf4j
public class WeixinPayServiceImpl implements WeixinPayService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private WeiXinPayProperties weiXinPayProperties;

    @Override
    public Map<String, Object> createNative(String orderNo, String remoteAddr) {

        // 根据订单号获取订单
        Order order = orderService.getOrderByOrderNo(orderNo);

        // 调用微信的统一下单Api
        HttpClientUtils client = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/unifiedorder");

        // 组装参数
        try {
            Map<String, String> params = new HashMap<>();
            params.put("appid", weiXinPayProperties.getAppId()); // 公众号id
            params.put("mch_id", weiXinPayProperties.getPartner()); // 商户号
            params.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串
            params.put("body", order.getCourseTitle()); // 商品描述
            params.put("out_trade_no", order.getOrderNo()); // 订单号
            params.put("total_fee", order.getTotalFee().intValue() + ""); // 订单金额(单位：分)
            params.put("spbill_create_ip", remoteAddr); // 终端地址
            params.put("notify_url", weiXinPayProperties.getNotifyUrl()); // 通知地址(回调地址)
            params.put("trade_type", "NATIVE"); // 交易类型

            // 将参数转换成xml格式字符串，并且在字符串的最后追加计算的签名
            String xmlParams = WXPayUtil.generateSignedXml(params, weiXinPayProperties.getPartnerKey());
            log.info("\n" + xmlParams);

            // 将参数放入请求对象的方法体
            client.setXmlParam(xmlParams);
            // 使用https协议传输
            client.setHttps(true);
            // 使用post方式发送请求
            client.post();
            // 得到响应
            String resultXml = client.getContent();
            log.info("\n resultXml：\n" + resultXml);

            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);

            //错误处理
            if ("FAIL".equals(resultMap.get("return_code")) || "FAIL".equals(resultMap.get("result_code"))) {
                log.error("微信支付统一下单错误 - "
                        + "return_code: " + resultMap.get("return_code")
                        + "return_msg: " + resultMap.get("return_msg")
                        + "result_code: " + resultMap.get("result_code")
                        + "err_code: " + resultMap.get("err_code")
                        + "err_code_des: " + resultMap.get("err_code_des"));

                throw new GuliException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
            }

            // 要组装的结果对象
            Map<String, Object> map = new HashMap<>();
            map.put("result_code",resultMap.get("result_code")); // 交易标识
            map.put("code_url",resultMap.get("code_url")); // 二维码url
            map.put("course_id",order.getCourseId()); // 课程id
            map.put("total_fee",order.getTotalFee()); // 课程价格
            map.put("out_trade_no",order.getOrderNo()); // 订单号

            return map;
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
        }
    }

}
