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

    List<Buyer> findByLastname (String lastname);

    @Query(value = "SELECT buyer.lastname from buyer WHERE buyer.lastname = :id", nativeQuery = true) //todo
    List<Buyer> findBuyersByShipmentAndAmount(@Param("title") String title, @Param("amount") Integer integer);

    @Query(value = "SELECT player_image.path FROM player_image WHERE player_image.player_id = :id LIMIT 1", nativeQuery = true) //todo
    String findBuyersByMinMax(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query(value = "SELECT player_image.lastname from player_image WHERE player_image.player_id = :id", nativeQuery = true) //todo
    List<Buyer> findBadBuyers(@Param("count") Integer integer);


//    {
//        "criterias": [
//        {"lastName": "Иванов"}, //Фамилия
//        {"productName": "Минеральная вода", "minTimes": 5}, // Название товара и число раз
//        {"minExpenses": 112, "maxExpenses": 4000}, //Минимальная и максимальная стоимость всех покупок
//        {"badCustomers": 3} //Число пассивных покупателей
//      ]
//    }


}
