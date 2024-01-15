package com.ioansavulescu.ecommerce.dao;

import com.ioansavulescu.ecommerce.entity.Comanda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comenzi", path = "comenzi")
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

    Page<Comanda> findByClientEmailOrderByCreareDataDesc(@Param("email") String email, Pageable pageable);
}
