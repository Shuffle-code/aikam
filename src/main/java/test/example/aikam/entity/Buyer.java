package test.example.aikam.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Table (name = "buyer")
@EntityListeners(AuditingEntityListener.class)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "purchase",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "shipment_id"))
    private Set<Shipment> shipments = new HashSet<>();

    @Override
    public String toString() {
        return "{\"lastname\":\"" + lastname + "\"" +
                ",\"firstname\":\"" + firstname + "\"" +
                '}';
    }
}
