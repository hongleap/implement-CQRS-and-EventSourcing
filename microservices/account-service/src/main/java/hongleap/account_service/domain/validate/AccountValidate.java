package hongleap.account_service.domain.validate;

import hongleap.account_service.domain.exception.AccountDomainException;
import hongleap.common.domain.valueObject.AccountStatus;
import hongleap.common.domain.valueObject.AccountTypeCode;
import hongleap.common.domain.valueObject.Currency;
import hongleap.common.domain.valueObject.Money;

import java.math.BigDecimal;

public class AccountValidate {

    // validate accountNumber
    public static void validateAccountNumber(String accountNumber){
        if (accountNumber == null){
            throw new AccountDomainException("Account number cannot be null!");
        }

        if (!accountNumber.matches("^\\d{9}$")){
            throw new AccountDomainException("Account number is valid");
        }
    }

    // validate accountTypeCode
    public static void validateAccountTypeCode(AccountTypeCode accountTypeCode){
        if (accountTypeCode != AccountTypeCode.SAVING){
            throw new AccountDomainException("Account type can be only SAVING");
        }
    }

    // validate initialBalance
    public static void validateInitialBalance(Money initialBalance){
        Money minIntialBalance = new Money(BigDecimal.valueOf(10), Currency.USD);
        if (initialBalance.isLessThan(minIntialBalance)){
            throw new AccountDomainException("Create new account is required 10$");
        }
    }

    // validate accountActive
    public static void validateAccountActive(AccountStatus status){
        if (status != AccountStatus.ACTIVE){
            throw new IllegalStateException(
                    "Transaction not allowed Account status is: " + status
            );
        }
    }

    // vaidate transactionAmount
    public static void validateTransactionAmount(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Transaction amount must not be null");
        }
        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
    }

    // validateBalance
    public static void validateSufficientBalance(Money balance, Money withdrawAmount) {
        if (balance.amount().compareTo(withdrawAmount.amount()) < 0) {
            throw new IllegalStateException(
                    "Insufficient funds. Balance: " + balance.amount()
                            + ", Requested: " + withdrawAmount.amount());
        }
    }

}
