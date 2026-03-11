package hongleap.account_service.application;

import hongleap.account_service.application.dto.create.request.CreateAccountRequest;
import hongleap.account_service.application.dto.create.request.DepositMoneyRequest;
import hongleap.account_service.application.dto.create.request.FrozenRequest;
import hongleap.account_service.application.dto.create.request.WithdrawMoneyRequest;
import hongleap.account_service.application.dto.create.response.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    DepositMoneyResponse depositAmount(DepositMoneyRequest request);

    WithdrawMoneyResponse withdrawalAmount(WithdrawMoneyRequest request);

    FrozenResponse freezeAccount(FrozenRequest request);
}
