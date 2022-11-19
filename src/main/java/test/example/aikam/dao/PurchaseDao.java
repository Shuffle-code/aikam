package test.example.aikam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.example.aikam.entity.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseDao extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT * FROM purchase WHERE data BETWEEN :startData AND :endData", nativeQuery = true)
    List<Purchase> findAllByData (@Param("startData") LocalDate startData, @Param("endData") LocalDate endData);
}
