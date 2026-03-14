package hongleap.account_service.application;

import hongleap.account_service.application.dto.create.CreateAccountRequest;
import hongleap.account_service.application.dto.create.CreateAccountResponse;
import hongleap.account_service.application.dto.deposit.DepositMoneyRequest;
import hongleap.account_service.application.dto.freeze.FrozenRequest;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import hongleap.account_service.application.dto.deposit.DepositMoneyResponse;
import hongleap.account_service.application.dto.freeze.FrozenResponse;
import hongleap.account_service.application.dto.withdraw.WithdrawMoneyResponse;
import hongleap.account_service.application.mapper.AccountMapper;
import hongleap.account_service.application.port.input.service.AccountService;
import hongleap.account_service.domain.command.CreateAccountCommand;
import hongleap.account_service.domain.command.DepositMoneyCommand;
import hongleap.account_service.domain.command.FreezeAccountCommand;
import hongleap.account_service.domain.command.WithdrawMoneyCommand;
import hongleap.common.domain.valueObject.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final  AccountMapper accountMapper;
    private final CommandGateway commandGateway;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        log.info("createAccountRequest: {}", createAccountRequest);

        AccountId newAccountId = new AccountId(UUID.randomUUID());
        CreateAccountCommand createAccountCommand = accountMapper
                .createAccountRequestToCreateAccountCommand(
                        new AccountId(UUID.randomUUID()),createAccountRequest);
        log.info("createAccountId {}", newAccountId);

        AccountId accountId = commandGateway.sendAndWait(createAccountCommand);

        return CreateAccountResponse.builder()
                .accountId(createAccountCommand.accountId().value())
                .accountTypeCode(createAccountCommand.accountTypeCode())
                .accountHolder(createAccountCommand.accountHolder())
                .initialBalance(createAccountCommand.initialBalance())
                .message("Account created successfully")
                .build();

    }

    @Override
    public DepositMoneyResponse depositAmount(DepositMoneyRequest request) {
        log.info("DepositMoneyResponse: {}", request);

        DepositMoneyCommand depositMoneyCommand = accountMapper.toDepositMoneyCommand(
                request.accountId(),
                request.transactionId(),
                request.amount(),
                request.remark()
        );

        commandGateway.sendAndWait(depositMoneyCommand);

        return DepositMoneyResponse.builder()
                .amount(request.amount())
                .remark(request.remark())
                .build();
    }

    @Override
    public WithdrawMoneyResponse withdrawalAmount(WithdrawMoneyRequest request) {
        log.info("WithdrawMoneyResponse: {}", request);

        WithdrawMoneyCommand withdrawMoneyCommand = accountMapper.toWithdrawMoneyCommand(
                request.accountId(),
                request.transactionId(),
                request.amount(),
                request.remark()
        );

        commandGateway.sendAndWait(withdrawMoneyCommand);

        return WithdrawMoneyResponse.builder()
                .amount(request.amount())
                .remark(request.remark())
                .build();
    }

    @Override
    public FrozenResponse freezeAccount(FrozenRequest request) {
        log.info("FrozenResponse: {}", request);

        FreezeAccountCommand freezeAccountCommand = new FreezeAccountCommand(
                request.accountId(),
                request.remark(),
                request.requestedBy()
        );

        commandGateway.sendAndWait(freezeAccountCommand);

        return FrozenResponse.builder()
                .accountId(request.accountId())
                .remark(request.remark())
                .build();
    }


}


