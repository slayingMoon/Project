package com.tsh.frantishex.reservationService.dao;


import com.tsh.frantishex.reservationService.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenericRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}
