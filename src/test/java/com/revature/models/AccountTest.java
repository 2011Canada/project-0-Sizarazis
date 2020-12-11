package com.revature.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AccountTest {

	private Account account;
	
	@BeforeEach
	public void setAccount() {
		account = new Account();
	}
	
	@Test
	public void TestBalance() {
		assertEquals(1, 1);
	}
	
	@Test
	public void FailTest() {
		fail();
	}
	
}
