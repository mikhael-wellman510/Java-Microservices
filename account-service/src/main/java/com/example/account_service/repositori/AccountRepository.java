package com.example.account_service.repositori;

import com.example.account_service.db.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account getFirstByEmail(String email);
}
