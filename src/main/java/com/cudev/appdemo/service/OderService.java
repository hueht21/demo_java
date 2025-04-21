package com.cudev.appdemo.service;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Customer;
import com.cudev.appdemo.entity.Oder;
import com.cudev.appdemo.entity.Repairman;
import com.cudev.appdemo.model.request.OderRequestDTO;
import com.cudev.appdemo.model.request.RepairManOderRequest;
import com.cudev.appdemo.model.response.OderDTO;
import com.cudev.appdemo.repository.CustomerRepository;
import com.cudev.appdemo.repository.OderRepository;
import com.cudev.appdemo.repository.RepairManRepository;
import com.cudev.appdemo.service.redis.OderServiceRedis;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class OderService {


    @Autowired
    private OderRepository oderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RepairManRepository repairManRepository;

    @Autowired
    private OderServiceRedis oderServiceRedis;

//    @Autowired
//    private UserRepository userRepository;

    @Transactional
    public ReponseObject  createOder(OderRequestDTO oderRequestDTO) {

        try {
            // Kiểm tra Customer có tồn tại không
            Customer customer = customerRepository.findById(oderRequestDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + oderRequestDTO.getCustomerId()));

            // Tạo Oder mới
            Oder oder = new Oder();
            oder.setCustomer(customer);
            oder.setStatus(oderRequestDTO.getStatus());
            oder.setAddress(oderRequestDTO.getAddress());
            oder.setNameCustomer(oderRequestDTO.getNameCustomer());
            oder.setNumberPhoneCustomer(oderRequestDTO.getNumberPhoneCustomer());
            oder.setDescribe(oderRequestDTO.getDescribe());

            // Lưu ảnh
            MultipartFile imgFile = oderRequestDTO.getImg();
            if (imgFile != null && !imgFile.isEmpty()) {
                String imageUrl = fileStorageService.saveFile(imgFile);
                oder.setImg(imageUrl);
            } else {
                oder.setImg(null);
            }

            Oder savedOder =  oderRepository.save(oder);
            return new ReponseObject(true, "Tạo đơn hàng thành công", savedOder);
        } catch (Exception e) {
            return new ReponseObject(false, "Tạo đơn hàng thất bại: " + e.getMessage(), null);
        }
    }

    @Transactional
    public ReponseObject updateRepairMan(RepairManOderRequest repairManOderRequest) {
        try {

            Optional<Repairman> repairman = repairManRepository.findById(repairManOderRequest.idRepairMan);

            if (repairman.isEmpty()) {
                return new ReponseObject(false, "Không tìm thấy thợ sửa", null);
            }

            if (repairman.get().getStatus() == 0) {
                return new ReponseObject(false, "Thợ sửa đã tạm khoá", null);
            }

            Oder oder = oderRepository.findById(repairManOderRequest.getIdOder()).get();
            oder.setStatus("Đã có thợ nhận");
            oder.setStatusCode(2);
            oder.setRepairman(repairman.get());
            oderRepository.save(oder);

            return new ReponseObject(true, "Đã cập nhập thợ sửa thành công", oder);


        } catch (Exception e) {
            return new ReponseObject(false, "Đã có lỗi cập nhập thợ", null);
        }
    }


    @Transactional
    public ReponseObject getOderResponse(Long userId) {

        try {
            Optional<Customer> customer = customerRepository.findById(userId);

            if (customer.isEmpty()) {
                return new ReponseObject(false, "Không tìm thấy khách hàng", null);
            }

            List<OderDTO> listOder = oderServiceRedis.getOderByUser(userId);

            return new ReponseObject(true, "Lấy dữ liệu thành công", listOder);

        } catch (Exception e) {

            System.out.println(e);

            return new ReponseObject(false, "Đã có lỗi xảy ra", null);
        }
    }

}
