package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto create(AccountTypeDto accountType);

    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

    AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic);

    AccountType getAccountTypeDBEntitiesByMnemonic(String mnemonic);

    AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic);
}
