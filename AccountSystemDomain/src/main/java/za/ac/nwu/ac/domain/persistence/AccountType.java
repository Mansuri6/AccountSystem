package za.ac.nwu.ac.domain.persistence;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@NonNull
@Getter
@Setter
@Table(name = "ACCOUNT_TYPE", schema = "DEMO_SCHEMA")
public class AccountType implements Serializable {

@Id
@SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "DEMO_SCHEMA.ACCOUNT_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
@Column(name = "ACCOUNT_TYPE_ID")
private Long accountTypeId;

@Column(name = "MNEMONIC")
private String mnemonic;

@Column(name = "ACCOUNT_TYPE_NAME")
private String accountTypeName;
@Column(name = "CREATION_DATE")
private LocalDate creationDate;

@OneToMany(targetEntity = AccountTransaction.class, fetch = FetchType.LAZY, mappedBy = "accountType")
private Set<AccountTransaction> accountTransactions;

    public AccountType(String mnemonic, String accountTypeName, LocalDate creationDate) {
        this.accountTypeName = accountTypeName;
        this.mnemonic =mnemonic;
        this.creationDate = creationDate;
    }
}
