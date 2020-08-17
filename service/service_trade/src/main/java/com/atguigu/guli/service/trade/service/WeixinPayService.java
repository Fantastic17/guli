package com.atguigu.guli.service.trade.service;

import java.util.Map;

/**
 * @author wind
 * @create 2020-08-02 17:06
 */
public interface WeixinPayService {

    Map<String, Object> createNative(String orderNo, String remoteAddr);
}
