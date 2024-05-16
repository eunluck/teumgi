package com.teumgi.api.error;


import com.teumgi.api.utils.MessageUtils;

public class NotFoundAlgorithmException extends ServiceRuntimeException {

    static final String MESSAGE_KEY = "error.notfound.algorithm";

    static final String MESSAGE_DETAILS = "error.notfound.algorithm.details";

    public NotFoundAlgorithmException(String targetName) {
        super(MESSAGE_KEY, MESSAGE_DETAILS, new Object[]{targetName});
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(getDetailKey(), getParams());
    }

    @Override
    public String toString() {
        return MessageUtils.getMessage(getMessageKey());
    }
}
