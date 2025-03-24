package com.cudev.appdemo.controllers.oder;


import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Oder;
import com.cudev.appdemo.model.request.OderRequestDTO;
import com.cudev.appdemo.model.request.RepairManOderRequest;
import com.cudev.appdemo.service.OderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/orders")
public class OderController {


    @Autowired
    private OderService oderService;

    @PostMapping("/create")
    public ResponseEntity<ReponseObject> createOder(@RequestParam("customerId") Long customerId, @RequestParam("status") String status, @RequestParam("address") String address, @RequestParam("nameCustomer") String nameCustomer, @RequestParam("numberPhoneCustomer") String numberPhoneCustomer, @RequestParam("describe") String describe, @RequestParam(value = "img", required = false) MultipartFile img) {
        OderRequestDTO oderRequestDTO = new OderRequestDTO();
        oderRequestDTO.setCustomerId(customerId);
        oderRequestDTO.setStatus(status);
        oderRequestDTO.setAddress(address);
        oderRequestDTO.setNameCustomer(nameCustomer);
        oderRequestDTO.setNumberPhoneCustomer(numberPhoneCustomer);
        oderRequestDTO.setDescribe(describe);
        oderRequestDTO.setImg(img);

        try {
            Oder savedOder = oderService.createOder(oderRequestDTO);
            return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Thành công", savedOder), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Thất bại", e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-repair-oder")
    public ResponseEntity<ReponseObject> updateRepairMan(@RequestBody @Valid RepairManOderRequest repairManOderRequest) {
        try {
            ReponseObject reponseObject = oderService.updateRepairMan(repairManOderRequest);
            return new ResponseEntity<ReponseObject>(reponseObject, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Thất bại", null), HttpStatus.BAD_REQUEST);
        }
    }

}
