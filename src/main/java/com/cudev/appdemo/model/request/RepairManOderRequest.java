package com.cudev.appdemo.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairManOderRequest {


    public Long idRepairMan;

    public String numberPhoneRepairMan;

    public String nameRepairMan;

//    public String idCustomer;

    public Long idOder;


}
