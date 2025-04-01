package com.cudev.appdemo.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReponseObject {

    private Boolean status;

    private String message;

    private Object data;
}
