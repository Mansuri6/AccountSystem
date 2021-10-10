package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.MemberDetailsDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.UpdateAccountTransactionFlow;

@RestController
@RequestMapping("member_transaction")
public class MemberController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private final UpdateAccountTransactionFlow updateAccountTransactionFlow;

    @Autowired
    public MemberController(FetchAccountTransactionFlow fetchAccountTransactionFlow, UpdateAccountTransactionFlow updateAccountTransactionFlow) {
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.updateAccountTransactionFlow = updateAccountTransactionFlow;
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add miles for a new member", notes = "Add miles for a new member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "miles added successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> add(
            @ApiParam(value = "RequestBody to create a add miles for account transaction", required = true)
            @RequestBody MemberDetailsDto memberDetailsDto
    ){

        AccountTransactionDto accountTransactionResponse = updateAccountTransactionFlow.updateAdd(memberDetailsDto);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/subtract")
    @ApiOperation(value = "Subtract miles for a new member", notes = "Subtract miles for a new member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "miles Subtracted successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    }
    )
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> delete(
            @ApiParam(value = "RequestBody to create a subtract miles for account transaction", required = true)
            @RequestBody MemberDetailsDto memberDetailsDto
    ){

        AccountTransactionDto accountTransactionResponse = updateAccountTransactionFlow.updateDelete(memberDetailsDto);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
