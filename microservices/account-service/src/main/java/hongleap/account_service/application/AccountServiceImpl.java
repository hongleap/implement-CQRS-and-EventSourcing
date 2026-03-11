package hongleap.account_service.application;

import hongleap.account_service.application.dto.create.request.CreateAccountRequest;
import hongleap.account_service.application.dto.create.request.DepositMoneyRequest;
import hongleap.account_service.application.dto.create.request.FrozenRequest;
import hongleap.account_service.application.dto.create.request.WithdrawMoneyRequest;
import hongleap.account_service.application.dto.create.response.*;
import hongleap.account_service.application.dto.query.FindAccountByIdQuery;
import hongleap.account_service.application.dto.query.FindAllAccountsQuery;
import hongleap.account_service.application.mapper.AccountMapper;
import hongleap.account_service.domain.command.CreateAccountCommand;
import hongleap.account_service.domain.command.DepositMoneyCommand;
import hongleap.account_service.domain.command.FreezeAccountCommand;
import hongleap.account_service.domain.command.WithdrawMoneyCommand;
import hongleap.common.domain.valueObject.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final  AccountMapper accountMapper;
    private final CommandGateway commandGateway;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {

//        CreateAccountCommand createAccountCommand = accountMapper
//                .createAccountRequestToCreateAccountCommand(
//                        new AccountId(UUID.randomUUID()),createAccountRequest);
//
//        log.info("CreateAccountCommand: {}", createAccountCommand);
//
//        // 2. Invoke and handle Axon command gateway
//        AccountId result = commandGateway.sendAndWait(createAccountCommand);
//        log.info("CreateAccountCommand result: {}", result);
//
//        return CreateAccountResponse.builder()
//                .accountId(createAccountCommand.accountId().value())
//                .accountTypeCode(createAccountCommand.accountTypeCode())
//                .accountHolder(createAccountCommand.accountHolder())
//                .initialBalance(createAccountCommand.initialBalance())
//                .message("Account created successfully")
//                .build();

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


