package com.ioansavulescu.ecommerce.dao;

import com.ioansavulescu.ecommerce.entity.Produs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


@RepositoryRestResource
public interface ProdusRepository extends JpaRepository<Produs, Long> {

    // query method
    Page<Produs> findByCategorieId(@Param("id") Long id, Pageable pageable);

    Page<Produs> findByNumeContaining(@Param("nume") String nume, Pageable pageable);
}
