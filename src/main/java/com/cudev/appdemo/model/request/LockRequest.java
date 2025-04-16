package com.cudev.appdemo.model.request;

import lombok.Data;

@Data
public class LockRequest {
    private Long userId;
    private int status;
}
