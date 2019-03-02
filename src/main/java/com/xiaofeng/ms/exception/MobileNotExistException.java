package com.xiaofeng.ms.exception;

import com.xiaofeng.ms.result.CodeMsg;

public class MobileNotExistException extends GlobalException {
    private static final long serialVersionUID = 1L;
    public MobileNotExistException() {
        super(CodeMsg.MOBILE_NOT_EXIST);
    }
    public CodeMsg getCm() {
        return super.getCm();
    }
}
