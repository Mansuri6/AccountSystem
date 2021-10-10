package za.ac.nwu.ac.translator.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeTranslatorImplTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @InjectMocks
    AccountTypeTranslatorImpl accountTypeTranslator;

    @Test
    public void getAllAccountTypesTest() {
        List<AccountTypeDto> accountTypeDtoList = new ArrayList<>();
        List<AccountType> accountTypeList = Arrays.asList(new AccountType("MILES", "MILES", LocalDate.now()));
        Mockito.when(accountTypeRepository.findAll()).thenReturn(accountTypeList);
        assertNotNull(accountTypeTranslator.getAllAccountTypes());
    }

    @Test
    public void createTest() {
        AccountTypeDto accountTypeDto = new AccountTypeDto("MILES", "MILES", LocalDate.now());
        Mockito.when(accountTypeRepository.save(accountTypeDto.getAccountType())).thenReturn(new AccountType());
        assertNotNull(accountTypeTranslator.create(accountTypeDto));
    }

    @Test
    public void getAccountTypeByMnemonicTest() {
        Mockito.when(accountTypeRepository.getAccountTypeByMnemonic(Mockito.anyString())).thenReturn(new AccountType("MILES", "MILES", LocalDate.now()));
        assertNotNull(accountTypeTranslator.getAccountTypeByMnemonic(Mockito.anyString()));
    }

    @Test
    public void getAccountTypeByMnemonicNativeQueryTest() {
        Mockito.when(accountTypeRepository.getAccountTypeByMnemonicNativeQuery(Mockito.anyString())).thenReturn(new AccountType("MILES", "MILES", LocalDate.now()));
        assertNotNull(accountTypeTranslator.getAccountTypeByMnemonicNativeQuery(Mockito.anyString()));
    }

    /*@Test
    public void getAccountTypeDBEntitiesByMnemonicTest() {

    }
*/
    @Test
    public void deleteAccountTypeByMnemonicTest() {
        Mockito.when(accountTypeRepository.deleteAccountTypeByMnemonic(Mockito.anyString())).thenReturn(new AccountType("MILES", "MILES", LocalDate.now()));
        assertNotNull(accountTypeTranslator.deleteAccountTypeByMnemonic(Mockito.anyString()));
    }
}