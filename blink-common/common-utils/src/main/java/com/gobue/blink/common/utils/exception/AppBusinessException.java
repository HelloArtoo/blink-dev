package com.gobue.blink.common.utils.exception;


import com.jd.y.ipc.saas.common.spring.error.CommonErrorCode;
import com.jd.y.ipc.saas.common.spring.error.ErrorCode;
import lombok.Data;

@Data
public class AppBusinessException extends BaseException {

	private static final long serialVersionUID = 8299312292335394970L;

	private static final ErrorCode DEFAULT_CODE = CommonErrorCode.INTERNAL_ERROR;

    private String code = DEFAULT_CODE.getCode();

    //Http状态码
    private int httpStatus = DEFAULT_CODE.getStatus();

    private Object[] args;

    public AppBusinessException(String code, int httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public AppBusinessException(String code, int httpStatus, String message, Object ... args) {
        this(code,httpStatus,message);
        this.args = args;
    }

    public AppBusinessException(String message) {
        super(message);
    }

    public AppBusinessException(String message, Object ... args) {
        super(message);
        this.args = args;
    }

    /**
     * @param errorCode 状态码, 这个字段会在错误信息里返回给客户端.
     * @param message
     */
    public AppBusinessException(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), errorCode.getStatus(), message);
    }

    public AppBusinessException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }


}