package com.tsh.clientManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsh.clientManager.model.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("Select c from Card c where c.client.phoneNumber = :phoneNumber")
	Optional<Card> findCardByClientPhoneNumber(String phoneNumber);
}
