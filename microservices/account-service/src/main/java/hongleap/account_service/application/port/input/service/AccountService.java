package hongleap.account_service.application.port.input.service;

import hongleap.account_service.application.dto.create.CreateAccountRequest;
import hongleap.account_service.application.dto.create.CreateAccountResponse;
import hongleap.account_service.application.dto.deposit.DepositMoneyRequest;
import hongleap.account_service.application.dto.freeze.FrozenRequest;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import hongleap.account_service.application.dto.deposit.DepositMoneyResponse;
import hongleap.account_service.application.dto.freeze.FrozenResponse;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyResponse;

public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    DepositMoneyResponse depositAmount(DepositMoneyRequest request);
    WithdrawMoneyResponse withdrawalAmount(WithdrawMoneyRequest request);
    FrozenResponse freezeAccount(FrozenRequest request);
}
