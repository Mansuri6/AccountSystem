package za.ac.nwu.ac.domain.persistence;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@NonNull
@Getter
@Setter
@Table(name = "ACCOUNT_TX_DETAILS", schema = "DEMO_SCHEMA")
public class AccountTransactionDetails implements Serializable {

    @Id
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "DEMO_SCHEMA.ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @Column(name = "ACCOUNT_TX_DETAILS_ID")
    private Long accountTransactionDetailsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TX_ID")
    private AccountTransaction accountTransaction;

    @Column(name = "PARTNER_NAME")
    private String partnerName;

    @Column(name = "NUMBER_OF_ITEMS")
    private Long numberOfItems;

    public AccountTransactionDetails(AccountTransaction accountTransaction, String partnerName, Long numberOfItems) {
        this.accountTransactionDetailsId = this.getAccountTransactionDetailsId();
        this.accountTransaction = accountTransaction;
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;

    }

    public AccountTransactionDetails(String partnerName, Long numberOfItems) {
        this.accountTransactionDetailsId = this.getAccountTransactionDetailsId();
        this.accountTransaction = this.getAccountTransaction();
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;

    }

    public AccountTransactionDetails(Long accountTransactionDetailsId, AccountTransaction accountTransaction, String partnerName, Long numberOfItems) {
        this.accountTransactionDetailsId = accountTransactionDetailsId;
        this.accountTransaction = accountTransaction;
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;
    }
}
