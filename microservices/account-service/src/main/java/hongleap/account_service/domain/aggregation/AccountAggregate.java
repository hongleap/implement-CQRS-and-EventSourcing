package hongleap.account_service.domain.aggregation;

import hongleap.account_service.domain.command.CreateAccountCommand;
import hongleap.account_service.domain.command.FreezeAccountCommand;
import hongleap.account_service.domain.event.AccountFrozenEvent;
import hongleap.common.domain.valueObject.Currency;
import hongleap.account_service.domain.command.DepositMoneyCommand;
import hongleap.account_service.domain.command.WithdrawMoneyCommand;
import hongleap.common.domain.event.AccountCreatedEvent;
import hongleap.account_service.domain.event.MoneyDepositedEvent;
import hongleap.account_service.domain.event.MoneyWithdrawnEvent;
import hongleap.account_service.domain.exception.AccountDomainException;
import hongleap.account_service.domain.validate.AccountValidate;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Money;
import hongleap.common.domain.valueObject.AccountId;
import hongleap.common.domain.valueObject.BranchId;
import hongleap.common.domain.valueObject.CustomerId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Slf4j
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private AccountId accountId;
    private String accountNumber;
    private String accountHolder;
    private CustomerId customerId;
    private AccountTypeCode accountTypeCode;
    private BranchId branchId;
    private Money balance;
    private AccountStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private void validateAccountIsActive() {
        if (this.status != AccountStatus.ACTIVE) {
            throw new AccountDomainException("Deposit is not allowed. Account status: " + this.status);
        }
    }

    private void validateAccountIsFreeze(){
        if (this.status == AccountStatus.CLOSED) {
            throw new AccountDomainException("Only ACTIVE accounts can be frozen. Current status: " + this.status);
        }
        if (this.status == AccountStatus.FREEZE){
            throw new AccountDomainException("Can not freeze this account! Account is freeze");
        }
    }

    private void validateDepositAmount(Money amount) {

        if (amount == null) {
            throw new AccountDomainException("Deposit amount cannot be null");
        }

        Money zero = new Money(BigDecimal.ZERO, Currency.USD);
        if (amount.isLessThanOrEqual(zero)) {
            throw new AccountDomainException("Deposit amount must be greater than zero");
        }
    }

    private void validateWithdrawalAmount(Money amount){
        if (amount == null) {
            throw new AccountDomainException("Withdrawal amount cannot be null");
        }
        Money zero = new Money(BigDecimal.ZERO, Currency.USD);
        if (amount.isLessThanOrEqual(zero)) {
            throw new AccountDomainException("Withdrawal amount must be greater than Zero");
        }
        if (this.balance == null){
            throw new AccountDomainException("Account balance is not initialized");
        }
        if (this.balance.isLessThan(amount)){
            throw new AccountDomainException("Insufficient balance!");
        }
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {

        log.info("Aggregate on create account command: {}", createAccountCommand);

        // validate account number
        AccountValidate.validateAccountNumber(createAccountCommand.accountNumber());

        // validate account type code
        AccountValidate.validateAccountTypeCode(createAccountCommand.accountTypeCode());

        // validate balance
        AccountValidate.validateInitialBalance(createAccountCommand.initialBalance());

        // create event objects
        AccountCreatedEvent accountCreatedEvent = AccountCreatedEvent.builder()
                .accountId(createAccountCommand.accountId())
                .accountNumber(createAccountCommand.accountNumber())
                .accountHolder(createAccountCommand.accountHolder())
                .customerId(createAccountCommand.customerId())
                .accountTypeCode(createAccountCommand.accountTypeCode())
                .branchId(createAccountCommand.branchId())
                .initialBalance(createAccountCommand.initialBalance())
                .createdAt(ZonedDateTime.now())
                .createdBy("ADMIN")
                .status(AccountStatus.ACTIVE)
                .build();

        // apply aggregate life cycle
        AggregateLifecycle.apply(accountCreatedEvent);
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.accountId();
        this.accountNumber = event.accountNumber();
        this.accountHolder = event.accountHolder();
        this.customerId = event.customerId();
        this.accountTypeCode = event.accountTypeCode();
        this.branchId = event.branchId();
        this.balance = event.initialBalance();
        this.createdAt = ZonedDateTime.now();
        this.createdBy = event.createdBy();
        this.status = event.status();
    }

    @CommandHandler
    public void handle(DepositMoneyCommand depositMoneyCommand) {

        log.info("Handling DepositMoneyCommand for accountId = {}", depositMoneyCommand.accountId().value());

        validateAccountIsActive();
        validateDepositAmount(depositMoneyCommand.amount());

        Money newBalance = this.balance.add(depositMoneyCommand.amount());

        AggregateLifecycle.apply(
                new MoneyDepositedEvent(
                        this.accountId,
                        this.customerId,
                        depositMoneyCommand.transactionId(),
                        depositMoneyCommand.amount(),
                        newBalance,
                        depositMoneyCommand.remark(),
                        ZonedDateTime.now()
                )
        );
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand withdrawMoneyCommand) {

        log.info("Handling WithdrawMoneyCommand for accountId = {}", withdrawMoneyCommand.accountId().value());

        validateAccountIsActive();
        validateWithdrawalAmount(withdrawMoneyCommand.amount());
        Money newBalance = this.balance.subtract(withdrawMoneyCommand.amount());
        AggregateLifecycle.apply(new MoneyWithdrawnEvent(
                this.accountId,
                this.customerId,
                withdrawMoneyCommand.transactionId(),
                withdrawMoneyCommand.amount(),
                newBalance,
                withdrawMoneyCommand.remark(),
                ZonedDateTime.now()
        ));
    }
    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event){
        // State change based on event
        this.balance = event.newBalance();
        log.info("MoneyWithdrawnEvent applied: accountId={}, newBalance={}",
                event.accountId().value(),
                this.balance
        );
    }

    @CommandHandler
    public void handle(FreezeAccountCommand command){
        log.info("Handling FreezeAccountCommand for accountId = {}", command.accountId().value());
        validateAccountIsFreeze();
        AggregateLifecycle.apply(new AccountFrozenEvent(
                this.accountId,
                this.customerId,
                this.status,
                AccountStatus.FREEZE,
                command.remark(),
                command.requestedBy(),
                ZonedDateTime.now()
        ));
    }
    @EventSourcingHandler
    public void on(AccountFrozenEvent event){
        this.status = event.newStatus();
        this.updatedAt = event.createdAt();
        this.updatedBy = event.requestedBy();
        log.info("AccountFrozenEvent applied: accountId={}, status={} -> {}",
                event.accountId().value(),
                event.previousStatus(),
                event.newStatus()
        );
    }

}
