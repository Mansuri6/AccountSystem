package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.MemberDetailsDto;

public interface UpdateAccountTransactionFlow {
        AccountTransactionDto updateAdd(MemberDetailsDto memberDetailsDto);

        AccountTransactionDto updateDelete(MemberDetailsDto memberDetailsDto);
    }

