package test.example.aikam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import test.example.aikam.dao.BuyerDao;
import test.example.aikam.entity.Buyer;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerDao buyerDao;
    public List<Buyer> findAll(){
        return buyerDao.findAll();
    }

    public List<Buyer> findByLastname (String lastname){
        return buyerDao.findByLastname(lastname);
    };

    public List<Buyer> findBuyersByShipmentAndAmount(String title, Integer integer){
        return buyerDao.findBuyersByShipmentAndAmount(title, integer);
    }

    public List<Buyer> findBadBuyers(Integer integer) {
        return buyerDao.findBadBuyers(integer);
    }

    public String findBuyersByMinMax(BigDecimal min,BigDecimal max){
        return buyerDao.findBuyersByMinMax(min, max);
    };


}
