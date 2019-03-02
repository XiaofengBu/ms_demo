package com.xiaofeng.ms.exception;

import com.xiaofeng.ms.result.CodeMsg;

public class LoginException extends GlobalException {
    private static final long serialVersionUID = 1L;
    public LoginException() {
        super(CodeMsg.PASSWORD_ERROR);
    }
    public CodeMsg getCm() {
        return super.getCm();
    }
}
