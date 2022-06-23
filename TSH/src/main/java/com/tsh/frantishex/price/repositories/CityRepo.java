package com.tsh.frantishex.price.repositories;

import com.tsh.frantishex.price.repositories.entities.PriceCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<PriceCity, Long> {
    PriceCity findPriceCityByName(String name);

    boolean existsByName(String name);
}
