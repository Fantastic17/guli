package com.atguigu.guli.service.ucenter.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wind
 * @create 2020-07-25 19:05
 */
@ConfigurationProperties(prefix = "wx.open")
@Component
@Data
public class UcenterProperties {

    private String appId;
    private String appSecret;
    private String redirectUri;
}
