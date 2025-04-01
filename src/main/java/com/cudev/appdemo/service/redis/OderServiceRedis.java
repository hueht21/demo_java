package com.cudev.appdemo.service.redis;

import com.cudev.appdemo.entity.Oder;
import com.cudev.appdemo.model.response.OderDTO;
import com.cudev.appdemo.repository.OderRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OderServiceRedis {

    private final OderRepository oderRepository;

    public OderServiceRedis(OderRepository oderRepository) {
        this.oderRepository = oderRepository;
    }

    @Cacheable(value = "oder", key = "#userId")
    public List<OderDTO> getOderByUser(Long userId) {
        List<Oder> oderList = oderRepository.findByIdCustomer(userId);
        return oderList.stream().map(OderDTO::new).collect(Collectors.toList());
    }

    @Cacheable(value = "oder", key = "#oder.customer.idCustomer")
    public Oder saveOder(Oder oder) {
        oderRepository.save(oder);
        return oder;
    }
}
