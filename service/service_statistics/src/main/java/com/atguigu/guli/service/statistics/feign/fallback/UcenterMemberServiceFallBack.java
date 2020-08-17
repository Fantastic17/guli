package com.atguigu.guli.service.statistics.feign.fallback;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wind
 * @create 2020-08-09 0:17
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public R countRegisterNum(String day) {
        log.error("熔断被执行");
        return R.ok().data("registerNum", 0);
    }
}
