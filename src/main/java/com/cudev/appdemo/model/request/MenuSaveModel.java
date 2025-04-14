package com.cudev.appdemo.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSaveModel {
    private String nameMenu;
    private String linkUri;
    private Integer userCreate;

}
