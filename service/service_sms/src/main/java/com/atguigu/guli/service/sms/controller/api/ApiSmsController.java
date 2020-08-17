package com.atguigu.guli.service.sms.controller.api;

import com.aliyuncs.exceptions.ClientException;
import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.FormUtils;
import com.atguigu.guli.common.base.util.RandomUtils;
import com.atguigu.guli.service.base.exception.GuliException;
import com.atguigu.guli.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wind
 * @create 2020-07-19 20:12
 */
//@CrossOrigin
@Api(tags = "短信管理")
@RestController
@RequestMapping("/api/sms")
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation(value = "发送验证码")
    @GetMapping("/send/{mobile}")
    public R getCode(@PathVariable String mobile) throws ClientException {

        // 校验手机号
        if (StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)) {
            log.error("手机号不正确");
            new GuliException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        // 生成验证码
        String checkCode = RandomUtils.getSixBitRandom();

        // 发送验证码
        smsService.send(mobile, checkCode);

        // 存储验证码到redis
        redisTemplate.opsForValue().set(mobile, checkCode,5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }

}
