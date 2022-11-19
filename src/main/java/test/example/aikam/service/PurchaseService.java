package test.example.aikam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.example.aikam.dao.PurchaseDao;
import test.example.aikam.entity.Purchase;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseDao purchaseDao;

    public List<Purchase> findAllByData(LocalDate start, LocalDate end){
        return purchaseDao.findAllByData(start, end);
    }
}
