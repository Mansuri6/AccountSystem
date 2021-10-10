package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface AccountTransactionReposity extends JpaRepository<AccountTransaction, Long> {
    AccountTransaction findByMemberId(Long memberId);
    @Transactional
    @Modifying
    @Query("UPDATE AccountTransaction set totalMiles=:totalMiles, transactionDate=:date WHERE memberId=:memberId")
    void updateAccountAmountByMemberId(Long memberId, Long totalMiles, LocalDate date);


}
