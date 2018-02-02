package com.dx.ss.data.rebate;

import com.dx.ss.data.rebate.model.UserAccountModel;
import com.dx.ss.data.rebate.service.AccountService;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RebateApplicationTests {

	@Autowired
	private AccountService accountService;

	@Test
	public void contextLoads() {

		String startTime = "2018-10-20";
		String endTime = "2018-10-21";
		List<UserAccountModel> accounts = accountService.getUserAccountList(startTime, endTime);
		Assert.assertEquals(Days.daysBetween(DateTime.parse(startTime), DateTime.parse(endTime)).getDays() + 1, accounts.size());
	}

}
