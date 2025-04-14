package com.cudev.appdemo.model.request;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleMenuUpdateDTO {

    private Long roleId;
    private List<Long> menuIds;
}
