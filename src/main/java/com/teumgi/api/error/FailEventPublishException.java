package com.teumgi.api.error;


import com.teumgi.api.utils.MessageUtils;

public class FailEventPublishException extends ServiceRuntimeException {
    static final String MESSAGE_KEY = "error.event.publish";

    static final String MESSAGE_DETAILS = "error.event.publish.details";

    public FailEventPublishException(String targetName) {
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
