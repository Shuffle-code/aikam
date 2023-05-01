package test.example.aikam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.example.aikam.entity.Buyer;

import java.util.List;
import java.util.Optional;

public interface BuyerDao extends JpaRepository<Buyer, Long> {
    @Query(value = "SELECT firstname, lastname  from buyer WHERE lastname = :lastname", nativeQuery = true)
    List<String> findByLastnameNew (@Param("lastname") String lastname);
    List<Buyer> findByLastname (String lastname);
    @Query(value = "WITH this_shipment AS (select * from purchase \n" +
            "JOIN shipment on purchase.shipment_id = shipment.id \n" +
            "where shipment.title =:title),\n" +
            "amount_buyer_info AS (select count(shipment_id) as count, \n" +
            "buyer_id from this_shipment group by buyer_id)\n" +
            "SELECT buyer_id from amount_buyer_info where count >= :i", nativeQuery = true) //todo
    List<Long> findBuyersByShipmentAndAmount(@Param("title") String title, @Param("i") int i);
    @Query(value = "WITH purchese AS (SELECT * FROM purchase),\n" +
            "shipment_without_title AS\n" +
            "(SELECT id, cost FROM shipment),\n" +
            "full_join AS\n" +
            "(SELECT * FROM purchese FULL JOIN shipment_without_title\n" +
            "ON purchese.shipment_id = shipment_without_title.id),\n" +
            "stat AS (SELECT buyer_id, shipment_id, cost FROM full_join),\n" +
            "summ AS (SELECT buyer_id, SUM(cost) AS SUM\n" +
            "FROM stat GROUP BY buyer_id ORDER BY buyer_id)\n" +
            "SELECT buyer_id FROM summ WHERE sum >=:min AND sum <=:max", nativeQuery = true) //todo
    List<Long> findBuyersByMinMax(@Param("min") int min, @Param("max") int max);
    @Query(value = "SELECT id from buyer JOIN bad_info ON buyer.id = buyer limit :amountBad", nativeQuery = true) //todo
    List<Long> findBadBuyers(@Param("amountBad") Long amountBad );
}

