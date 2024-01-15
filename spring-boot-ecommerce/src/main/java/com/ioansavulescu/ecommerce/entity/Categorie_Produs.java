package com.ioansavulescu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="categorie_produs")
// @Data - bug
@Getter
@Setter
public class Categorie_Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nume_categorie")
    private String nume_categorie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
    private Set<Produs> produse;
}
