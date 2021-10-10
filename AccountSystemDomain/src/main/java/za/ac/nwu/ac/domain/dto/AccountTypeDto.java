package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class AccountTypeDto implements Serializable {

    private String mnemonic;
    private String accountTypeName;
    private LocalDate creationDate;

    public AccountTypeDto(AccountType accountType){
        this.accountTypeName = accountType.getAccountTypeName();
        this.mnemonic = accountType.getMnemonic();
        this.creationDate = accountType.getCreationDate();
    }

    @ApiModelProperty(position = 1,
            value = "AccountType Mnemonic",
            name = "Mnemonic",
            notes = "Uniquely Identifies Account type",
            dataType = "java.lang.String",
            example = "Miles",
            required = true
    )
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @ApiModelProperty(position = 2,
            value = "AccountType Name",
            name = "Name",
            notes = "Name of the Account type",
            dataType = "java.lang.String",
            allowEmptyValue = false,
            example = "Miles",
            required = true
    )
    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @ApiModelProperty(position = 3,
            value = "AccountType creation date",
            name = "CreationDate",
            notes = "This is the date on which Account type was created",
            dataType = "java.lang.String",
            example = "2021-09-09",
            allowEmptyValue = true,
            required = true
    )
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @JsonIgnore
    public AccountType getAccountType(){
        return new AccountType(getMnemonic(),getAccountTypeName(), getCreationDate());
    }
}
