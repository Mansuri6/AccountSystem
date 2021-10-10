package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;

import java.util.List;

@RestController
@RequestMapping("account_transaction")
public class AccountTransactionController {

    private final CreateAccountTransactionFlow createAccountTransactionFlow;
    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;


    @Autowired
    public AccountTransactionController(CreateAccountTransactionFlow createAccountTransactionFlow, FetchAccountTransactionFlow fetchAccountTransactionFlow) {
        this.createAccountTransactionFlow = createAccountTransactionFlow;
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
    }


    @PostMapping("")
    @ApiOperation(value = "Creates a new account transaction", notes = "Create a new account transaction in DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account transaction got successfully created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> create(
            @ApiParam(value = "RequestBody to create a new account transaction", required = true)
            @RequestBody AccountTransactionDto accountTransaction
    ){
        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.create(accountTransaction);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll(){
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransaction();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{transactionId}")
    @ApiOperation(value = "Fetched the Specified account type", notes = "Fetches the AccountTransaction corresponding to the given transactionId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AccountTransaction found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> getAccountTransaction(
            @ApiParam(value = "Fetches a specified AccountTransaction",
                    name = "transactionId",
                    example = "50002",
                    required = true)
            @PathVariable("transactionId") final Long transactionId){
        AccountTransactionDto accountTransaction = fetchAccountTransactionFlow.getAccountTransactionById(transactionId);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true, accountTransaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
