package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;

public interface DeleteAccountTypeFlow {
    AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic);
}
