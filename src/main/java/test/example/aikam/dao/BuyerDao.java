package test.example.aikam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import test.example.aikam.entity.Buyer;

import java.math.BigDecimal;
import java.util.List;

public interface BuyerDao extends JpaRepository<Buyer, Long> {

    @Query(value = "SELECT firstname, lastname  from buyer WHERE lastname = :lastname", nativeQuery = true)
    List<String> findByLastnameNew (@Param("lastname") String lastname);buyer.lastname = :lastname", nativeQuery = true)
    List<Buyer> findByLastname (String lastname);
//
//    @Query(value = "SELECT buyer from buyer JOIN purchase ON buyer.id = shipment.id WHERE buyer.lastname = :id", nativeQuery = true) //todo
//    List<Buyer> findBuyersByShipmentAndAmount(@Param("title") String title, @Param("amount") Integer integer);
////
////    @Query(value = "SELECT player_image.path FROM player_image WHERE player_image.player_id = :id LIMIT 1", nativeQuery = true) //todo
//    String findBuyersByMinMax(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
////
//    @Query(value = "SELECT buyer from buyer WHERE player_image.player_id = :id", nativeQuery = true) //todo
//    List<Buyer> findBadBuyers(@Param("count") Integer integer);

}
