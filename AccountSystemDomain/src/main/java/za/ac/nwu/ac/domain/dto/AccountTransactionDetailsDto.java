package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class AccountTransactionDetailsDto implements Serializable {

    String partnerName;
    Long numberOfItems;

    public AccountTransactionDetailsDto(String partnerName, Long numberOfItems) {
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;
    }

    public AccountTransactionDetailsDto(AccountTransactionDetails details) {
        this.partnerName = details.getPartnerName();
        this.numberOfItems = details.getNumberOfItems();
    }

    @JsonIgnore
    public AccountTransactionDetails  buildAccountTransactionDetails(AccountTransaction accountTransaction){
        return new AccountTransactionDetails(accountTransaction,this.getPartnerName(), this.getNumberOfDetails());
    }

    @JsonIgnore
    public AccountTransactionDetails  buildAccountTransactionDetails(){
        return new AccountTransactionDetails(this.getPartnerName(), this.getNumberOfDetails());
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Long getNumberOfDetails() {
        return numberOfItems;
    }

    public void setNumberOfDetails(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
