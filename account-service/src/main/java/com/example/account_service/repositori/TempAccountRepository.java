package com.example.account_service.repositori;

import com.example.account_service.db.entity.Account;
import com.example.account_service.db.entity.TempAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempAccountRepository extends CrudRepository<TempAccount,String> {
    TempAccount getFirstByEmail(String email);
}
