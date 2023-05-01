package test.example.aikam.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "buyer_id")
    private Long buyerId;
    @Column(name = "shipment_id")
    private Long shipmentId;
    @Column(name = "data")
    private LocalDate data;

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", shipmentId=" + shipmentId +
                ", data=" + data +
                '}';
    }
}
