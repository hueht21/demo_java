package com.cudev.appdemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterRequest {

    private String userName;
    private String password;
    private String nameCustomer;
    private String numberPhone;
    private String address;
    private Set<Long> roleIds; // Danh sách ID của Role


}
