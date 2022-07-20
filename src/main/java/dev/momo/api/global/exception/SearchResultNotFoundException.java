package dev.momo.api.global.exception;

import dev.momo.api.global.response.BaseResponseStatus;

public class SearchResultNotFoundException extends CustomBaseException {
    public SearchResultNotFoundException() {
        super(BaseResponseStatus.NOT_FOUND_CATEGORY_EXCEPTION);
    }

    public SearchResultNotFoundException(String message) {
        super(message);
    }

    public SearchResultNotFoundException(BaseResponseStatus status) {
        super(status);
    }
}
