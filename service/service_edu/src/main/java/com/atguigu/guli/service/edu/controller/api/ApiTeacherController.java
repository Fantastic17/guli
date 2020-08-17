package com.atguigu.guli.service.edu.controller.api;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wind
 * @create 2020-07-14 16:50
 */
//@CrossOrigin // 允许跨域
@Slf4j
@Api(tags = "讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list).message("获取讲师列表成功");
    }

    @ApiOperation("获取讲师")
    @GetMapping("/get/{id}")
    public R get(
            @ApiParam(value = "讲师id",required = true)
            @PathVariable String id){
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }
}

