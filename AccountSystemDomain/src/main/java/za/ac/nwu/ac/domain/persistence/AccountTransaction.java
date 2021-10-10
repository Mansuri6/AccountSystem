package za.ac.nwu.ac.domain.persistence;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@NonNull
@Getter
@Setter
@Table(name = "ACCOUNT_TX", schema = "DEMO_SCHEMA")
public class AccountTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "DEMO_SCHEMA.ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @Column(name = "TX_ID")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private AccountType accountType;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "TOTAL_MILES")
    private Long totalMiles;

    @Column(name = "TX_DATE")
    private LocalDate transactionDate;

    @OneToOne(targetEntity = AccountTransactionDetails.class, fetch = FetchType.LAZY, mappedBy = "accountTransaction")
    private AccountTransactionDetails accountTransactionDetails;

    public AccountTransaction(Long transactionId, AccountType accountType, Long memberId, Long amount, Long totalMiles,LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.totalMiles = totalMiles;
        this.transactionDate = transactionDate;
    }
}
