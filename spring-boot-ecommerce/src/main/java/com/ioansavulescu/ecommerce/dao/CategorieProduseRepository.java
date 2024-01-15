package com.ioansavulescu.ecommerce.dao;

import com.ioansavulescu.ecommerce.entity.Categorie_Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource(collectionResourceRel = "categorie_produs", path = "categorie_produs")
public interface CategorieProduseRepository extends JpaRepository<Categorie_Produs, Long> {

}
