package com.atguigu.guli.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wind
 * @create 2020-07-29 22:03
 */
@Data
public class MemberDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//会员id
    private String mobile;//手机号
    private String nickname;//昵称

}
