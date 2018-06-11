package fr.xebia.architectures.hexagonal.infra;

import fr.xebia.architectures.hexagonal.domain.account.AccountOperationApplicationProviderImpl;
import fr.xebia.architectures.hexagonal.domain.provider.application.AccountOperationApplicationProvider;
import fr.xebia.architectures.hexagonal.domain.provider.service.CurrencyServiceProvider;
import fr.xebia.architectures.hexagonal.domain.service.CurrencyService;
import fr.xebia.architectures.hexagonal.infra.service.CurrencyServiceProviderMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CurrencyServiceProvider currencyServiceProvider() {
        return new CurrencyServiceProviderMock();
    }

    @Bean
    public CurrencyService currencyService(@Autowired CurrencyServiceProvider currencyServiceProvider) {
        return new CurrencyService(currencyServiceProvider);
    }

    @Bean
    public AccountOperationApplicationProvider accountOperationApplicationProvider(@Autowired CurrencyService currencyService) {
        return new AccountOperationApplicationProviderImpl(currencyService);
    }

}
