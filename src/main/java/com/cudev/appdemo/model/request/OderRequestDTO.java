package com.cudev.appdemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OderRequestDTO {

    private Long customerId; // ID của Customer
    private String status;
    private String address;
    private String nameCustomer;
    private String numberPhoneCustomer;
    private String describe;
//    private String img;
    private MultipartFile img; // Lưu file ảnh thay vì URL
}
