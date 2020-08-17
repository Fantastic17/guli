package com.atguigu.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wind
 * @create 2020-07-22 17:28
 */
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;

}
