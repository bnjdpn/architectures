package fr.xebia.architectures.hexagonal.infra;

import fr.xebia.architectures.hexagonal.domain.currency.CurrencyChangeRate;
import fr.xebia.architectures.hexagonal.domain.operation.Deposit;
import fr.xebia.architectures.hexagonal.domain.operation.MakeDeposit;
import fr.xebia.architectures.hexagonal.domain.operation.MakeWithdraw;
import fr.xebia.architectures.hexagonal.domain.operation.Withdraw;
import fr.xebia.architectures.hexagonal.infra.service.FixedCurrencyChangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CurrencyChangeRate currencyChangeRate() {
        return new FixedCurrencyChangeRate();
    }

    @Bean
    public Deposit deposit(@Autowired CurrencyChangeRate currencyChangeRate) {
        return new MakeDeposit(currencyChangeRate);
    }

    @Bean
    public Withdraw withdraw(@Autowired CurrencyChangeRate currencyChangeRate) {
        return new MakeWithdraw(currencyChangeRate);
    }

}
