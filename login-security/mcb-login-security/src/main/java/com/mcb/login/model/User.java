package com.mcb.login.model;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)	
	private String username;

	@Column
	private String password;

	@Column
	private String first_name;

	@Column
	private String last_name;

	@Column
	private boolean enabled;

	@Column
	private int failed_attempt;

	@Column
	private Date lock_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getFailed_attempt() {
		return failed_attempt;
	}

	public void setFailed_attempt(int failed_attempt) {
		this.failed_attempt = failed_attempt;
	}

	public Date getLock_time() {
		return lock_time;
	}

	public void setLock_time(Date lock_time) {
		this.lock_time = lock_time;
	}

}
