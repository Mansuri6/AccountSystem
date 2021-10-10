package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.MemberDetailsDto;
import za.ac.nwu.ac.domain.exception.InvalidRequestException;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.UpdateAccountTransactionFlow;
import za.ac.nwu.ac.repo.persistence.AccountTransactionReposity;

import java.time.LocalDate;

@Component
public class UpdateAccountTransactionFlowImpl implements UpdateAccountTransactionFlow {


    private FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private AccountTransactionReposity accountTransactionReposity;

    @Autowired
    public UpdateAccountTransactionFlowImpl(FetchAccountTransactionFlow fetchAccountTransactionFlow, AccountTransactionReposity accountTransactionReposity) {
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.accountTransactionReposity = accountTransactionReposity;
    }


    @Override
    public AccountTransactionDto updateAdd(MemberDetailsDto memberDetailsDto) {
        AccountTransactionDto accountTransactionResponse = fetchAccountTransactionFlow.getAccountTransactionByMemberId(memberDetailsDto.getMemberId());
        accountTransactionResponse.setTotalMiles( accountTransactionResponse.getTotalMiles() == null ? 0 : accountTransactionResponse.getTotalMiles() + memberDetailsDto.getMiles());
        accountTransactionResponse.setTransactionDate(LocalDate.now());
        accountTransactionReposity.updateAccountAmountByMemberId(accountTransactionResponse.getMemberId(),accountTransactionResponse.getTotalMiles(),accountTransactionResponse.getTransactionDate());
        return accountTransactionResponse;
    }

    @Override
    public AccountTransactionDto updateDelete(MemberDetailsDto memberDetailsDto) {
        AccountTransactionDto accountTransactionResponse = fetchAccountTransactionFlow.getAccountTransactionByMemberId(memberDetailsDto.getMemberId());
        if (accountTransactionResponse.getTotalMiles() != null && accountTransactionResponse.getTotalMiles() >= memberDetailsDto.getMiles()) {
            accountTransactionResponse.setTotalMiles(accountTransactionResponse.getTotalMiles() - memberDetailsDto.getMiles());
            accountTransactionReposity.updateAccountAmountByMemberId(accountTransactionResponse.getMemberId(),accountTransactionResponse.getTotalMiles(),accountTransactionResponse.getTransactionDate());
            return accountTransactionResponse;
        } else {
            throw new InvalidRequestException("Don't have sufficient miles");
        }
    }
}
