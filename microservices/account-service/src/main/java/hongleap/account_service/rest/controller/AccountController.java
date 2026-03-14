package hongleap.account_service.rest.controller;

import hongleap.account_service.application.port.input.service.AccountService;
import hongleap.account_service.application.dto.create.CreateAccountRequest;
import hongleap.account_service.application.dto.create.CreateAccountResponse;
import hongleap.account_service.application.dto.deposit.DepositMoneyRequest;
import hongleap.account_service.application.dto.freeze.FrozenRequest;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import hongleap.account_service.application.dto.deposit.DepositMoneyResponse;
import hongleap.account_service.application.dto.freeze.FrozenResponse;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/accounts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(
            @Valid @RequestBody CreateAccountRequest createAccountRequest) {

        log.info("createAccount: {}", createAccountRequest);
        return accountService.createAccount(createAccountRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/deposit")
    public DepositMoneyResponse createDepositResponse(@RequestBody DepositMoneyRequest request) {
        DepositMoneyResponse response = accountService.depositAmount(request);
        log.info("Deposit amount : {}", response.amount());
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/withdrawal")
    public WithdrawMoneyResponse withdrawMoneyResponse(@RequestBody WithdrawMoneyRequest request){
        WithdrawMoneyResponse response = accountService.withdrawalAmount(request);
        log.info("Withdrawal amount : {}", response.amount());
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/frozen")
    public FrozenResponse createFrozenResponse(@RequestBody FrozenRequest frozenRequest){
        log.info("Frozen request : {}", frozenRequest);

        return accountService.freezeAccount(frozenRequest);
    }


}
