package com.revature.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AccountTest {

	private Account account = mock(Account.class);
	
	
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
	
	@Test
	public void testMock() {
		when(account.GetBalance()).thenReturn(1.0);
	}
}
