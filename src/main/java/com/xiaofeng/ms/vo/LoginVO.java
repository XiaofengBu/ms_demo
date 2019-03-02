package com.xiaofeng.ms.vo;

import com.xiaofeng.ms.validation.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    private String password;
}
