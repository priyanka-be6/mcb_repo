package com.mcb.login;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.login.model.User;

@Service
@Transactional
public class LoginService {

	public static final int MAX_FAILED_ATTEMPTS = 3;

	private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; 

	@Autowired
	private LoginRepository repo;

	public String loadLogin(String username, String password) {
		String message = null;
		User userExists = repo.findByUsername(username);
		if (userExists == null) {
			message = "user doesn't exists";
		} else {
			User user = repo.findByUserPass(username, md5(password));
			if (user == null) {
				if (userExists.getFailed_attempt() < MAX_FAILED_ATTEMPTS) {
					increaseFailedAttempts(userExists);
					message = "login failed";
				} else {
					long timeNow = Calendar.getInstance().getTimeInMillis();
					Timestamp date = new Timestamp(timeNow);
					repo.setLockTime(date, username);
					message = "user locked";
				}
			} else {
				repo.updateFailedAttempts(0, true, user.getUsername());
				message = "success";
			}

		}
		return message;
	}

	public void createLogin(User usersRequest) {
		User newUser = new User();
		newUser.setUsername(usersRequest.getUsername());
		newUser.setPassword(md5(usersRequest.getPassword()));
		newUser.setEnabled(true);
		newUser.setFirst_name(usersRequest.getFirst_name());
		newUser.setLast_name(usersRequest.getLast_name());
		repo.save(newUser);
	}

	public void listUsers() {
		List<User> users = repo.findByEnabled(false);
		for (User user : users) {
			unlockWhenTimeExpired(user);
		}
	}

	public void increaseFailedAttempts(User user) {
		int newFailAttempts = user.getFailed_attempt() + 1;
		repo.updateFailedAttempts(newFailAttempts, false, user.getUsername());
	}

	public void resetFailedAttempts(String email) {
		repo.updateFailedAttempts(0, true, email);
	}

	public boolean unlockWhenTimeExpired(User user) {
		long lockTimeInMillis = user.getLock_time().getTime();
		long currentTimeInMillis = System.currentTimeMillis();

		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setEnabled(true);
			user.setLock_time(null);
			user.setFailed_attempt(0);
			repo.save(user);
			return true;
		}

		return false;
	}

	public String md5(String password) {
		String encodedPassword = null;
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.reset();
			alg.update(password.getBytes());
			byte[] msgDigest = alg.digest();

			BigInteger number = new BigInteger(1, msgDigest);

			encodedPassword = number.toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encodedPassword;
	}

}
