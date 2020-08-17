package com.atguigu.guli.service.cms.controller.api;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.cms.entity.Ad;
import com.atguigu.guli.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wind
 * @create 2020-07-17 23:37
 */
//@CrossOrigin
@Api(tags = "广告推荐")
@Slf4j
@RequestMapping("/api/cms/ad")
@RestController
public class ApiAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("/list/{adTypeId}")
    public R listByTypeId(@ApiParam(value = "推荐位id", required = true)
                          @PathVariable String adTypeId) {

        List<Ad> adList = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items", adList);
    }


    @PostMapping("/save-test")
    public R saveAd(@RequestBody Ad ad) {

        redisTemplate.opsForValue().set("ad", ad);
        return R.ok();
    }


    @GetMapping("/get-test/{key}")
    public R getAd(@PathVariable String key) {

        Ad ad = (Ad) redisTemplate.opsForValue().get(key);
        return R.ok().data("ad", ad);
    }

    @DeleteMapping("/remove-test/{key}")
    public R removeAd(@PathVariable String key) {

        Boolean result = redisTemplate.delete(key);
        System.out.println(result);
        Boolean aBoolean = redisTemplate.hasKey(key);
        System.out.println(aBoolean);
        return R.ok();
    }

}
