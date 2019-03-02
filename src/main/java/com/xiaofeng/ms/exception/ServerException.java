package com.xiaofeng.ms.exception;

import com.xiaofeng.ms.result.CodeMsg;

public class ServerException extends GlobalException {
    private static final long serialVersionUID = 1L;
    public ServerException() {
        super(CodeMsg.SERVER_ERROR);
    }
    public CodeMsg getCm() {
        return super.getCm();
    }
}
