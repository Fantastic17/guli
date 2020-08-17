package com.atguigu.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wind
 * @create 2020-07-20 4:23
 */
@Data
public class RegisterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String mobile;
    private String code;
    private String password;

}
