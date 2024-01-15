package com.ioansavulescu.ecommerce.dao;


import com.ioansavulescu.ecommerce.entity.Tara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource(collectionResourceRel = "tari", path = "tari")
public interface TaraRepository extends JpaRepository<Tara, Integer> {

}
