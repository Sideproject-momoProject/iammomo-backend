package dev.momo.api.global.exception;

import dev.momo.api.global.response.BaseResponseStatus;

public class InvalidParamException extends CustomBaseException {

    public InvalidParamException() {
        super(BaseResponseStatus.INVALID_VALUE_PARAM);
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(BaseResponseStatus status) {
        super(status);
    }


}
