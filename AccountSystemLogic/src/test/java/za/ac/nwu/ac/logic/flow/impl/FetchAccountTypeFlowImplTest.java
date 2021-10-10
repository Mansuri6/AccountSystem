package za.ac.nwu.ac.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.translator.impl.AccountTypeTranslatorImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchAccountTypeFlowImplTest {

    @Mock
    private AccountTypeTranslatorImpl accountTypeTranslatorImpl;

    @InjectMocks
    private FetchAccountTypeFlowImpl fetchAccountTypeFlow;

    @Test
    public void getAllAccountTypesTest() {
        List<AccountTypeDto> accountList = new ArrayList<>();
       // AccountTypeDto a = new AccountTypeDto("Test", "MILES", LocalDate.now());
        accountList.add(new AccountTypeDto("Test", "MILES", LocalDate.now()));

        Mockito.when(accountTypeTranslatorImpl.getAllAccountTypes()).thenReturn(accountList);
        assertNotNull(fetchAccountTypeFlow.getAllAccountTypes());
    //    Mockito.verify(fetchAccountTypeFlow.getAllAccountTypes());
    }

    @Test
    public void getAccountTypeByMnemonic() {
        Mockito.when(accountTypeTranslatorImpl.getAccountTypeByMnemonic(Mockito.anyString())).thenReturn(new AccountTypeDto());
        assertNotNull(fetchAccountTypeFlow.getAccountTypeByMnemonic(Mockito.anyString()));
  //      Mockito.verify(fetchAccountTypeFlow.getAccountTypeByMnemonic(Mockito.anyString()));
    }

    @Test
    public void getAccountTypeDBEntitiesByMnemonic() {
        Mockito.when(accountTypeTranslatorImpl.getAccountTypeDBEntitiesByMnemonic(Mockito.anyString())).thenReturn(new AccountType());
        assertNotNull(fetchAccountTypeFlow.getAccountTypeDBEntitiesByMnemonic(Mockito.anyString()));
     //   Mockito.verify(fetchAccountTypeFlow.getAccountTypeDBEntitiesByMnemonic(Mockito.anyString()));
    }
}