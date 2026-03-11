package hongleap.account_service.rest.exception;

import hongleap.common.application.exception.ErrorResponse;
import hongleap.common.domain.exception.DomainException;
import hongleap.account_service.domain.exception.AccountDomainException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class AccountException {

    @ExceptionHandler(DomainException.class)
    public ErrorResponse handleAccountDomainException(AccountDomainException e){
        return ErrorResponse.builder()
                .staus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
    }

}
