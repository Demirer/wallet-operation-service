package com.example.wallet_operation_service.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Wallet wallet SET wallet.balance = (wallet.balance + :budgedAdjustment) WHERE wallet.customerId = :customerId")
    void updateBalance(@Param("budgedAdjustment") Double budgedAdjustment, @Param("customerId") Long customerId);
}
