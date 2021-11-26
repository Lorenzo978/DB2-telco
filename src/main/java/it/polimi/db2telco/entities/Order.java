package it.polimi.db2telco.entities;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Long id_package;
    private Long id_validity_period;
    private Timestamp created_at;
    private int status;

    @JoinColumn(name = "id_user")
    private User id_user;

}
