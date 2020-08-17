package com.atguigu.guli.service.statistics.feign;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.statistics.feign.fallback.UcenterMemberServiceFallBack;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wind
 * @create 2020-08-09 0:15
 */
@FeignClient(value = "service-ucenter",fallback = UcenterMemberServiceFallBack.class)
@Service
public interface UcenterMemberService {


    @GetMapping("/admin/ucenter/member/count-register-num/{day}")
    R countRegisterNum(@PathVariable("day") String day);
}
