package com.example.otp_service.db.repositori;

import com.example.otp_service.db.entity.TempOTP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempOTPRepositori extends CrudRepository<TempOTP,String> {
    TempOTP getFirstByEmail(String email);
}
