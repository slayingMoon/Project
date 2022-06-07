package com.tsh.clientManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsh.clientManager.model.entities.PointTransaction;

@Repository
public interface PointTransactionRepository
		extends
			JpaRepository<PointTransaction, Long> {
}
