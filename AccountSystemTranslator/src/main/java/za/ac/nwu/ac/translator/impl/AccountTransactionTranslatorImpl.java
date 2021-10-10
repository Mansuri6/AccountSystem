package za.ac.nwu.ac.translator.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.exception.AccountTypeCreationException;
import za.ac.nwu.ac.domain.exception.DatabaseFindFetchException;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.repo.persistence.AccountTransactionReposity;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {

    private final AccountTransactionReposity repo;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionReposity repo) {
        this.repo = repo;
    }


    @Override
    public List<AccountTransaction> getAllAccountTransactions() {
        List<AccountTransaction> accountTransactions;
        try {
            accountTransactions =  repo.findAll();
            log.info("All the Account Transaction retrieved from database");
        } catch (RuntimeException e){
            log.error("Can't read the data from database");
            throw new DatabaseFindFetchException("Unable to read from DB", e);
        }
        return accountTransactions;
    }

    @Override
    public AccountTransaction getAccountTransactionByPk(Long transactionId) {
        try {
            return repo.findById(transactionId).orElse(null);
        } catch (RuntimeException e){
            log.error("Can't read the data from database");
            throw new DatabaseFindFetchException("Unable to read from DB", e);
        }
    }

    @Override
    public AccountTransaction save(AccountTransaction accountTransaction) {
        try {
            return repo.save(accountTransaction);
        } catch (RuntimeException e){
            throw new AccountTypeCreationException("Unable to save to DB", e);
        }
    }

    @Override
    public AccountTransaction getAccountTransactionByMemeberId(Long memberId) {
        try {
            AccountTransaction transaction = repo.findByMemberId(memberId);
            if (!Optional.ofNullable(transaction).isPresent()){
                throw new DatabaseFindFetchException("Member id not found : " + String.valueOf(memberId));
            }
            return transaction;
        } catch (RuntimeException e){
            log.error("Can't read the data from database");
            throw new DatabaseFindFetchException("Unable to read from DB", e);
        }
    }
}
