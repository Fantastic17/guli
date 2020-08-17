package com.atguigu.guli.service.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wind
 * @create 2020-06-22 0:18
 */
@ApiModel("Teacher查询对象")
@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty("讲师级别")
    private Integer level;

    @ApiModelProperty("开始时间")
    private String joinDateBegin;

    @ApiModelProperty("结束时间")
    private String joinDateEnd;

}
