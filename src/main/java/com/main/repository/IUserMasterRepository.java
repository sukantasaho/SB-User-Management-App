package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.main.entity.UserMaster;
import java.util.List;


public interface IUserMasterRepository extends JpaRepository<UserMaster, Integer> 
{
        public UserMaster  findByEmail(String email);
        @Modifying
        @Query(value = "UPDATE USER_MASTER SET status = 'A', password=?1 WHERE email =?2", nativeQuery = true)
        public void updatePasswordAndActivateAccount(String newPassword, String email);
}
