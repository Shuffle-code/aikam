package test.example.aikam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import test.example.aikam.entity.Shipment;

import java.math.BigDecimal;

public interface ShipmentDao extends JpaRepository<Shipment, Long> {
}
