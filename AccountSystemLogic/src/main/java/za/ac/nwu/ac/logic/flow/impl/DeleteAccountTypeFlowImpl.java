package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.DeleteAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;


@Transactional
@Component
public class DeleteAccountTypeFlowImpl implements DeleteAccountTypeFlow {

    private AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public DeleteAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        return accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
    }
}
