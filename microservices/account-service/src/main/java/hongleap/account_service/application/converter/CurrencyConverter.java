package hongleap.account_service.application.converter;

import hongleap.common.domain.valueObject.Currency;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency currency) {
        return currency == null ? null : currency.name();
    }

    @Override
    public Currency convertToEntityAttribute(String value) {
        return value == null ? null : Currency.valueOf(value);
    }
}