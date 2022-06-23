package com.tsh.frantishex.clientManagerService.repository;


import com.tsh.frantishex.clientManagerService.model.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
