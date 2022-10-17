package com.mcb.login;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mcb.login.model.User;

public interface LoginRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	@Query(value = "select * from users u where u.username = ?1 and u.password = ?2", nativeQuery = true)
	User findByUserPass(String username,String password);

	@Query(value = "UPDATE users u SET u.failed_attempt = ?1, u.enabled = ?2 WHERE u.username = ?3", nativeQuery = true)
	@Modifying
	public void updateFailedAttempts(int failAttempts,boolean isEnabled, String username);
	
	@Query(value = "UPDATE users u SET u.lock_time = ?1 WHERE u.username = ?2", nativeQuery = true)
	@Modifying
	public void setLockTime(Date locktime, String username);
	
	@Query(value = "select * from users u where u.enabled = ?1", nativeQuery = true)
	List<User> findByEnabled(boolean enabled);
}
