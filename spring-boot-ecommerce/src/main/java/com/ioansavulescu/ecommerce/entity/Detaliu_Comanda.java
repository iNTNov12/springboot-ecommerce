package com.ioansavulescu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="detaliu_comanda")
@Getter
@Setter
public class Detaliu_Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="url_imagine")
    private String url_imagine;

    @Column(name="pret_unitar")
    private String pret_unitar;

    @Column(name="cantitate")
    private String cantitate;

    @Column(name="id_produs")
    private String id_produs;

    @ManyToOne
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

}
