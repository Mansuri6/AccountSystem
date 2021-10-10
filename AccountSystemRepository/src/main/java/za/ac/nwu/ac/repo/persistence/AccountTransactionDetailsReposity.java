package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface AccountTransactionDetailsReposity extends JpaRepository<AccountTransactionDetails, Long> {
   /* @Transactional
    @Modifying
    @Query("UPDATE MemberTransaction set totalMiles=:totalMiles, transactionDate=:date WHERE memberId=:memberId")
    void updateAccountAmountByMemberId(Long memberId, Long totalMiles, LocalDate date);*/
}
