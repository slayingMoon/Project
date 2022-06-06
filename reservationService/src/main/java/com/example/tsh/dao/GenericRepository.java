package com.example.tsh.dao;

import com.example.tsh.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface GenericRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}
