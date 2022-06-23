package com.tsh.frantishex.clientManagerService.repository;

import com.tsh.frantishex.clientManagerService.model.entities.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PointTransactionRepository
		extends
			JpaRepository<PointTransaction, Long> {
}
