package com.atguigu.guli.service.trade.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wind
 * @create 2020-08-02 16:40
 */
@Data
@Component
@ConfigurationProperties(prefix = "weixin.pay")
public class WeiXinPayProperties {

    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;
}
