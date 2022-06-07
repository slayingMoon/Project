package com.tsh.clientManager.repository;

import com.tsh.clientManager.model.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.client.phoneNumber = :phoneNumber")
    Sale findFirstByClientPhoneNumber(String phoneNumber);
}
