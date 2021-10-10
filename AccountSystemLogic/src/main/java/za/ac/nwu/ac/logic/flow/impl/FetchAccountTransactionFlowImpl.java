package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class FetchAccountTransactionFlowImpl implements FetchAccountTransactionFlow {

    private AccountTransactionTranslator translator;

    @Autowired
    public FetchAccountTransactionFlowImpl(AccountTransactionTranslator translator) {
        this.translator = translator;
    }


    @Override
    public List<AccountTransactionDto> getAllAccountTransaction() {
        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        for (AccountTransaction accountTransaction : translator.getAllAccountTransactions()){
            accountTransactionDtos.add(new AccountTransactionDto(accountTransaction));
        }
        return accountTransactionDtos;
    }

    @Override
    public AccountTransactionDto getAccountTransactionById(Long transactionId) {
        AccountTransaction accountTransaction = translator.getAccountTransactionByPk(transactionId);
        return null != accountTransaction ? new AccountTransactionDto(accountTransaction) : null;
    }

    @Override
    public AccountTransactionDto getAccountTransactionByMemberId(Long memberId) {
        AccountTransaction accountTransaction = translator.getAccountTransactionByMemeberId(memberId);
        return null != accountTransaction ? new AccountTransactionDto(accountTransaction) : null;
    }
}
