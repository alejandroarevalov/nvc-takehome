package com.nvc.backendtest.util;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Builder
public class JsonResponse extends HashMap<String, Object> {

    public void setField(String key, Object value) {
        this.put(key, value);
    }

    public class Fields {
        public static final String SUCCESS_MESSAGE = "message";
        public static final String ERROR_MESSAGE = "errorMessage";
    }
}
