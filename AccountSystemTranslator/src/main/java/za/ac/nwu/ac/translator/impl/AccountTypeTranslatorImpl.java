package za.ac.nwu.ac.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.exception.AccountTypeCreationException;
import za.ac.nwu.ac.domain.exception.AccountTypeNotFoundException;
import za.ac.nwu.ac.domain.exception.DatabaseFindFetchException;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {

    private final Logger logger = LoggerFactory.getLogger(AccountTypeTranslatorImpl.class);

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        List<AccountTypeDto> accountTypeDtoList = new ArrayList<>();
        try {
            for (AccountType accountType : accountTypeRepository.findAll()) {
                accountTypeDtoList.add(new AccountTypeDto(accountType));
            }
        } catch (RuntimeException ex){
            logger.error("Unable to read from DB", ex);
            throw new DatabaseFindFetchException("Unable to read from DB", ex);
        }
        logger.info("Account Type retrieved {} ", accountTypeDtoList);
        return accountTypeDtoList;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto) {
        try {
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountType());
            logger.info("Account Type crated {} ", accountType);
            return new AccountTypeDto(accountType);
        } catch (RuntimeException ex){
            logger.error("Unable to read from DB", ex);
            throw new AccountTypeCreationException("Unable to save into DB", ex);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        try {
            logger.info("Request for mnemonic : {}", mnemonic);
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            logger.info("Account Type retrieved {} ", accountType.toString());
            return new AccountTypeDto(accountType);
        } catch (RuntimeException ex) {
            logger.error("Unable to read from DB", ex);
            throw new DatabaseFindFetchException("Unable to read from DB", ex);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic) {
        try {
            logger.info("Request for mnemonic : {}", mnemonic);
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonicNativeQuery(mnemonic);
            logger.info("Account Type retrieved {} ", accountType);
            return new AccountTypeDto(accountType);
        } catch (RuntimeException ex) {
            logger.error("Unable to read from DB", ex);
            throw new DatabaseFindFetchException("Unable to read from DB", ex);
        }
    }

    @Override
    public AccountType getAccountTypeDBEntitiesByMnemonic(String mnemonic) {
        AccountType accountType;
        try {
            logger.info("Request for mnemonic : {}", mnemonic);
            accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            logger.info("Account Type retrieved {} ", accountType);
        } catch (RuntimeException ex) {
            logger.error("Unable to read from DB", ex);
            throw new DatabaseFindFetchException("Unable to read from DB", ex);
        }
        return accountType;
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) throws DatabaseFindFetchException, AccountTypeNotFoundException{
        try {
            logger.info("Delete request for mnemonic : {}", mnemonic);
            AccountType accountType = accountTypeRepository.deleteAccountTypeByMnemonic(mnemonic);
            if (!Optional.ofNullable(accountType).isPresent()) {
                logger.error("Account Type not available for {}", mnemonic);
                throw new AccountTypeNotFoundException("Account Type not available");
            }
            logger.info("Account Type deleted {} ", accountType.toString());
            return new AccountTypeDto(accountType);
        } catch (RuntimeException ex) {
            logger.error("Account Type not available for {}", mnemonic);
            throw new DatabaseFindFetchException("Unable to read from DB", ex);
        }
    }
}
