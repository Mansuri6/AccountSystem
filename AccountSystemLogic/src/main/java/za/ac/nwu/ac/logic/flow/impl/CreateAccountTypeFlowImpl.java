package za.ac.nwu.ac.logic.flow.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component(value = "createAccountTypeFlow")
@Slf4j
public class CreateAccountTypeFlowImpl implements CreateAccountTypeFlow {

    private final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public CreateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountType) {
    log.info("Account Type Create Request {}", accountType);
        if (null == accountType.getCreationDate()){
            accountType.setCreationDate(LocalDate.now());
        }
        return accountTypeTranslator.create(accountType);
    }
}
