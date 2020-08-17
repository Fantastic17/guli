package com.atguigu.guli.service.sms.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author wind
 * @create 2020-07-19 20:14
 */
public interface SmsService {

    void send(String mobile, String checkCode) throws ClientException;
}
