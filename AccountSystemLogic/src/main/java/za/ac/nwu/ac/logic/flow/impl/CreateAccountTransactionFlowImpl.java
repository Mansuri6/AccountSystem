package za.ac.nwu.ac.logic.flow.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.exception.AccountTransactionCreationException;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
@Slf4j
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private AccountTransactionTranslator translator;
    private FetchAccountTypeFlow fetchAccountTypeFlow;
    private AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;

    @Autowired
    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator translator, FetchAccountTypeFlow fetchAccountTypeFlow, AccountTransactionDetailsTranslator accountDetailsTranslator) {
        this.translator = translator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.accountTransactionDetailsTranslator = accountDetailsTranslator;
    }


    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto) {
        accountTransactionDto.setTransactionId(null);
        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDBEntitiesByMnemonic(accountTransactionDto.getAccountTypeMnemonic());
        if (null == accountType){
            log.error("Account Type not found for Mnemonic : {}", accountTransactionDto.getAccountTypeMnemonic());
            throw new AccountTransactionCreationException("Account Transaction creation exeption");
        }
        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);

        AccountTransaction createdAccountTransaction = translator.save(accountTransaction);
        if (null != accountTransactionDto.getDetails()){
            AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails().buildAccountTransactionDetails(createdAccountTransaction);
            accountTransactionDetailsTranslator.save(accountTransactionDetails);
        }
        return new AccountTransactionDto(createdAccountTransaction);
    }
}
