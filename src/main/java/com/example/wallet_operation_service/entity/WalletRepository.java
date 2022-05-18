package com.example.wallet_operation_service.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Wallet wallet SET Wallet.balance = (Wallet.balance + :budgedAdjustment) WHERE Wallet.customerId = :customerId")
    void updateBalance(@Param("budgedAdjustment") Double budgedAdjustment, @Param("customerId") Long customerId);
}
