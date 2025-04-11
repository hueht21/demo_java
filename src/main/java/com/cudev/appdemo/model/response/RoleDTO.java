package com.cudev.appdemo.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
public class RoleDTO {

    private Long idRole;
    private String nameRole;
    private String describe;
}
