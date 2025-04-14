package com.cudev.appdemo.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {

    private Long id;
    private String menuName;
    private String menuUrl;
}
