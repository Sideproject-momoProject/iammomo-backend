package dev.momo.api.global.exception;

import dev.momo.api.global.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public abstract class CustomBaseException extends Exception {
    protected BaseResponseStatus status;

    public CustomBaseException() {
    }

    public CustomBaseException(String message) {
        super(message);
    }

    public CustomBaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public BaseResponseStatus getStatus() {
        return status;
    }
}
