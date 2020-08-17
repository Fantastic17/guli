package com.atguigu.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wind
 * @create 2020-07-07 3:24
 */
@Data
public class CourseQueryVo implements Serializable {

    private static final Long SerialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
