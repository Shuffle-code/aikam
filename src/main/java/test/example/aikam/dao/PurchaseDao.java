package test.example.aikam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test.example.aikam.entity.Purchase;

public interface PurchaseDao extends JpaRepository<Purchase, Long> {
}
