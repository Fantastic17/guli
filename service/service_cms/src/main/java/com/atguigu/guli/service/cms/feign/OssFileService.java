package com.atguigu.guli.service.cms.feign;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.cms.feign.impl.OssFileServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wind
 * @create 2020-07-17 20:09
 */
@Service
@FeignClient(value = "service-oss",fallback = OssFileServiceImpl.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);

}
