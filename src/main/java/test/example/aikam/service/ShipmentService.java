package test.example.aikam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.example.aikam.dao.ShipmentDao;
import test.example.aikam.entity.Shipment;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentDao shipmentDao;
    public Shipment findById(Long id){
        return shipmentDao.findById(id).get();
    }
}
