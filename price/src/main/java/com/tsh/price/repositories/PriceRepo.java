package com.tsh.price.repositories;

import com.tsh.price.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price,Long> {
}
