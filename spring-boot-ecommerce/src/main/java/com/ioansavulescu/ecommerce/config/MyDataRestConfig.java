package com.ioansavulescu.ecommerce.config;

import com.ioansavulescu.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    // injectare manager JPA
    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // oprim metodele HTTP pentru Produs: PUT, POST, DELETE
        opresteMetodeleHttp(Produs.class,config, theUnsupportedActions);
        opresteMetodeleHttp(Tara.class,config, theUnsupportedActions);
        opresteMetodeleHttp(Comanda.class,config, theUnsupportedActions);

        // oprim metodele HTTP pentru Categorie_Produs: PUT, POST, DELETE
        opresteMetodeleHttp(Categorie_Produs.class,config, theUnsupportedActions);
        opresteMetodeleHttp(State.class,config, theUnsupportedActions);

        // apelam o metoda ajutor pentru expunerea id-urilor
        exposeIds(config);

        // configurare mappare cors
        cors.addMapping( config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private static void opresteMetodeleHttp(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        // expunere id entitati
        //

        // preia o lista cu toate clasele entitatilor din entity manager
        Set<EntityType<?>> enitities = entityManager.getMetamodel().getEntities();

        // creeaza un vector de tipuri de entitati
        List<Class> enitityClasses = new ArrayList<>();

        // preia tipurile de entitati pentru entitati
        for(EntityType tempEntityType : enitities) {
            enitityClasses.add(tempEntityType.getJavaType());
        }

        // expunere id entitati pt vectorul de tipuri de entitate/domeniu
        Class[] domainTypes = enitityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);


    }
}
