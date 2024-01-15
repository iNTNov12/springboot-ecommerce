package com.ioansavulescu.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="client")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nume")
    private String nume;

    @Column(name="prenume")
    private String prenume;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Comanda> comenzi = new HashSet<>();

    public void add(Comanda comanda) {

        if(comanda != null) {

            if(comenzi == null) {
                comenzi = new HashSet<>();
            }

            comenzi.add(comanda);
            comanda.setClient(this);
        }
    }
}
