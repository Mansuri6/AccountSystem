package za.ac.nwu.ac.web.sb.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.DeleteAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTypeControllerTest {

    private static final String APP_URL = "/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL= APP_URL + "/account_type";

    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;
    @Mock
    private CreateAccountTypeFlow createAccountTypeFlow;
    @Mock
    private DeleteAccountTypeFlow deleteAccountTypeFlow;

    @InjectMocks
    private AccountTypeController accountTypeController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(accountTypeController).build();
    }

    @Test
    public void getAll() throws Exception {
        String expected = "{\"successful\":true,\"product\":[{\"mnemonic\":\"Miles\",\"accountTypeName\":\"Miles\",\"creationDate\":[2021,9,9]}]}";
        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto("Miles", "Miles", LocalDate.parse("2021-09-09")));
        Mockito.when(fetchAccountTypeFlow.getAllAccountTypes()).thenReturn(accountTypes);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get((String.format("%s/%s", ACCOUNT_TYPE_CONTROLLER_URL, "all")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();
        assertEquals(expected, result.getResponse().getContentAsString());

    }

    /*@Test
    public void create() {
    }

    @Test
    public void getAccountType() {
    }

    @Test
    public void deleteAccountType() {
    }*/
}