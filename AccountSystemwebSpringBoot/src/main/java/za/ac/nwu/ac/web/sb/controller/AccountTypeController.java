package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;

import java.util.List;

@RestController
@RequestMapping("account_type")
public class AccountTypeController {

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;

    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow, @Qualifier("createAccountTypeFlow") CreateAccountTypeFlow createAccountTypeFlow) {
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.createAccountTypeFlow = createAccountTypeFlow;
    }


    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account types", notes = "Required a list of Account Types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAll(){
    List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new account type", notes = "Create a new account in DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account type got successfully created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTypeDto>> create(
            @ApiParam(value = "RequestBody to create a new account type", required = true)
            @RequestBody AccountTypeDto accountType){
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("{mnemonic}")
    @ApiOperation(value = "Fetched the Specified account type", notes = "Fetches the account types corresponding to the given mnemonic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
            @ApiParam(value = "The Mnemonic that uniquely identifies account type",
                    name = "mnemonic",
                    example = "MILES",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){
        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
