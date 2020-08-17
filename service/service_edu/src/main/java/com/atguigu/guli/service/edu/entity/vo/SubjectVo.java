package com.atguigu.guli.service.edu.entity.vo;

import com.atguigu.guli.service.edu.entity.Subject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wind
 * @create 2020-07-04 23:48
 */
@Data
public class SubjectVo implements Serializable {

    private static final Long SerialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;

    private List<SubjectVo> children = new ArrayList<>();
}
