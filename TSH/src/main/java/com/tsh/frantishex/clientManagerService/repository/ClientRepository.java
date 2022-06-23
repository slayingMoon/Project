package com.tsh.frantishex.clientManagerService.repository;


import com.tsh.frantishex.clientManagerService.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findFirstByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
