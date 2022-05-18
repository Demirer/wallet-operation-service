package com.example.wallet_operation_service.model.response;

import com.example.wallet_operation_service.entity.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDetailResponse {

    private Wallet wallet;
}
