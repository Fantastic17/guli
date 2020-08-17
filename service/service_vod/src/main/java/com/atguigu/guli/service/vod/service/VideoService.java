package com.atguigu.guli.service.vod.service;


import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @author wind
 * @create 2020-07-12 3:41
 */

public interface VideoService {

    String uploadVideo(InputStream inputStream, String originalFilename);

    void removeVideo(String videoId) throws ClientException;

    void removeVideoByIdList(List<String> videoList) throws ClientException;

    String getPlayAuth(String videoSourceId) throws ClientException;
}
