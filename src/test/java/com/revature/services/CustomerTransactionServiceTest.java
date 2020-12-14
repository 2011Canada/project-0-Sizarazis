package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.repositories.IAccountDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.repositories.ITransactionLogDAO;
import com.revature.repositories.TransactionsLogPostgresDAO;

public class CustomerTransactionServiceTest {
	IAccountDAO accountDAO = mock(AccountPostgresDAO.class);
	ICustomerDAO customerDAO = mock(CustomerPostgresDAO.class);
	ITransactionLogDAO transactionDAO = mock(TransactionsLogPostgresDAO.class);
	
	CustomerTransactionService cts = new CustomerTransactionService(customerDAO, accountDAO, transactionDAO);
	
	@Test
	public void TestSimpleWithdrawal() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(80.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));

		try {
			cts.Withdraw(1, 20.00);
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
		} catch (NegativeNumberException e) {
			e.printStackTrace();
		}

		assertEquals(cts.CheckBalance(1), 80.00);
	}
	
	
	@Test
	public void TestNegativeWithdrawal() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));

		
		assertThrows(NegativeNumberException.class, () -> {
			cts.Withdraw(1, -20.00);
		});
		
		assertEquals(cts.CheckBalance(1), 100.00);
	}
	
	
	@Test
	public void TestNotEnoughToWithdraw() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));

		assertThrows(InsufficientFundsException.class, () -> {
			cts.Withdraw(1, 110.00);
		});
		
		assertEquals(cts.CheckBalance(1), 100.00);
	}
	
	
	@Test
	public void TestSimpleDeposit() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(110.33);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));

		try {
			cts.Deposit(1, 10.33);
		} catch (NegativeNumberException e) {
		}
		
		assertEquals(cts.CheckBalance(1), 110.33);
	}
	
	
	@Test
	public void TestNegativeDeposit() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));
		
		assertThrows(NegativeNumberException.class, () -> {
			cts.Deposit(1, -10.50);
		});
	}
	
	
	@Test
	public void TestSimpleTransfer() {
		Account a = new Account(1, 100.00);
		Account b = new Account(2, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(80.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));
		
		when(accountDAO.GetAccountBalance(b.GetAccountId())).thenReturn(120.00);
		when(accountDAO.FindAccountById(b.GetAccountId())).thenReturn(new Account(2, 100.00));
		
		try {
			cts.Transfer(a.GetAccountId(), b.GetAccountId(), 20.00);
		} catch (InsufficientFundsException | NegativeNumberException | UserNotFoundException e) {
			
		}
		
		assertEquals(cts.CheckBalance(a.GetAccountId()), 80.00);
		assertEquals(cts.CheckBalance(b.GetAccountId()), 120.00);
	}
	
	
	@Test
	public void TestNegativeTransfer() {
		Account a = new Account(1, 100.00);
		Account b = new Account(2, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));
		
		when(accountDAO.GetAccountBalance(b.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(b.GetAccountId())).thenReturn(new Account(2, 100.00));
		
		assertThrows(NegativeNumberException.class, () -> {
			cts.Transfer(1, 2, -10.00);
		});
	}
	
	
	@Test
	public void TestNotEnoughInBankAccountToTransfer() {
		Account a = new Account(1, 100.00);
		Account b = new Account(2, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));
		
		when(accountDAO.GetAccountBalance(b.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(b.GetAccountId())).thenReturn(new Account(2, 100.00));
		
		assertThrows(InsufficientFundsException.class, () -> {
			cts.Transfer(1, 2, 110.00);
		});
	}
	
	
	@Test
	public void TestTransferToNonexistantAccount() {
		Account a = new Account(1, 100.00);
		when(accountDAO.GetAccountBalance(a.GetAccountId())).thenReturn(100.00);
		when(accountDAO.FindAccountById(a.GetAccountId())).thenReturn(new Account(1, 100.00));
		
		assertThrows(UserNotFoundException.class, () -> {
			cts.Transfer(1, 2, 50.00);
		});
	}
}
