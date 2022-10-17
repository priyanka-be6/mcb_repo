package com.mcb.schecdule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mcb.login.LoginService;

@Component
public class SchedulingTask {

	@Autowired
	public LoginService loginService;
	
	@Scheduled (cron= "${mcb.schedule.time}")
	public void unlockUser () throws Exception {
		loginService.listUsers();
	}
}
