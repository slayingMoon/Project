package com.tsh.frantishex.clientManagerService.repository;

import java.util.Optional;

import com.tsh.frantishex.clientManagerService.model.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("Select c from Card c where c.client.phoneNumber = :phoneNumber")
	Optional<Card> findCardByClientPhoneNumber(String phoneNumber);
}
