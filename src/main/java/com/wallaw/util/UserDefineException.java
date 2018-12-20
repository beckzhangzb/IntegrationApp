package com.wallaw.util;

/**
 * @author zhangzb
 * @since 2018/8/21 17:01
 */
public class UserDefineException extends Throwable {
    private static final long serialVersionUID = -5317007026578376164L;
    private String errorCode;
    private String errorMsg;

    public UserDefineException(String errorCode, String errorMsg) {
        super(String.format("BusinessException{errorCode:%s, errorMsg:%s}", new Object[]{errorCode, errorMsg}));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public UserDefineException(String errorCode, String errorMsg, Throwable cause) {
        super(String.format("BusinessException{errorCode:%s, errorMsg:%s}", new Object[]{errorCode, errorMsg}), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
