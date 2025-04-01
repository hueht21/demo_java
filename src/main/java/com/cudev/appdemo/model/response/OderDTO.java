package com.cudev.appdemo.model.response;


import com.cudev.appdemo.entity.Oder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OderDTO  implements Serializable {

    private Long idOrder;
    private String status;
    private String address;
    private String nameCustomer;
    private String numberPhoneCustomer;
    private String describe;
    private String img;
    private int statusCode;
    private String nameRepairMan;

    public OderDTO(Oder oder) {
        this.idOrder = oder.getIdOrder();
        this.status = oder.getStatus();
        this.address = oder.getAddress();
        this.nameCustomer = oder.getNameCustomer();
        this.numberPhoneCustomer = oder.getNumberPhoneCustomer();
        this.describe = oder.getDescribe();
        this.img = oder.getImg();
        this.statusCode = oder.getStatusCode();
        this.nameRepairMan = oder.getRepairman() != null ? oder.getRepairman().getNameRepairman() : "";
    }
}
