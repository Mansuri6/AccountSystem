package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Query(value = "SELECT ACCOUNT_TYPE_ID, ACCOUNT_TYPE_NAME, CREATION_DATE, MNEMONIC FROM VITRSA_SANDBOX.DEMO_ACCOUNT_TYPE WHERE MNEMONIC=:mnemonic", nativeQuery = true)
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);

    /*
    HQL
     */
    @Query("FROM AccountType WHERE mnemonic=:mnemonic")
    AccountType getAccountTypeByMnemonic(String mnemonic);

    @Query("SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto(at.mnemonic, at.accountTypeName, at.creationDate )  FROM AccountType at WHERE mnemonic=:mnemonic")
    AccountTypeDto getAccountTypeDTOByMnemonic(String mnemonic);

    @Modifying
    @Query("DELETE FROM AccountType WHERE mnemonic=:mnemonic")
    AccountType deleteAccountTypeByMnemonic(String mnemonic);
}
