package com.gobue.blink.common.utils.spring.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BooleanWrapper {
    public static BooleanWrapper TRUE = new BooleanWrapper(true);
    public static BooleanWrapper FALSE = new BooleanWrapper(false);
    private boolean success;
    private String message;

    public BooleanWrapper(boolean success) {
        this(success, null);
    }

    @JsonCreator
    public BooleanWrapper(
            @JsonProperty("success") boolean success,
            @JsonProperty("message") String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public static BooleanWrapper of(boolean status) {
        return status ? TRUE : FALSE;
    }

}