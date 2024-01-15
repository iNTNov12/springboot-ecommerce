package com.ioansavulescu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nume")
    private String nume;

    @ManyToOne
    @JoinColumn(name="id_tara")
    private Tara tara;
}
