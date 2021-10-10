package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.repo.persistence.AccountTransactionDetailsReposity;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class AccountTransactionDetailsTranslatorImpl implements AccountTransactionDetailsTranslator {

    private AccountTransactionDetailsReposity accountTransactionDetailsReposity;

    @Autowired
    public AccountTransactionDetailsTranslatorImpl(AccountTransactionDetailsReposity accountTransactionDetailsReposity) {
        this.accountTransactionDetailsReposity = accountTransactionDetailsReposity;
    }


    @Override
    public AccountTransactionDetails save(AccountTransactionDetails accountTransactionDetails) {
        try{
           return accountTransactionDetailsReposity.save(accountTransactionDetails);
        } catch (RuntimeException e){
            throw new RuntimeException("Unable to save to DB", e);
        }
    }
}
