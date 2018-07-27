package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;


public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Venu", "7036071319",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Shankar", "8008985352",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Siva", "9177424331",new Wallet(new BigDecimal(7000)));
				
		 data.put("7036071319", cust1);
		 data.put("8008985352", cust2);	
		 data.put("9177424331", cust3);	
			/*service= new WalletServiceImpl(data);*/
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9908015612", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9908015612", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("Prasad", "999", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("Prasad", "", new BigDecimal(1500));
	}
	
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("Venu", "7036071319", new BigDecimal(9000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount6() 
	{
		service.createAccount("Venu", "7036071319", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("Srinu", "8008732530", new BigDecimal(5000));
		Customer expected=new Customer("Srinu", "8008732530", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testCreateAccount8() 
	{
		Customer actual=service.createAccount("venu", "8754922472", new BigDecimal(0));
		Customer expected=new Customer("venu", "8754922472", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance9() 
	{
		service.showBalance(null);		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance11() 
	{
		service.showBalance("12345");		
	}
	
	
	
	@Test
	public void testShowBalance12() 
	{
		Customer customer=service.showBalance("7036071319");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer13() 
	{
		service.fundTransfer("9948484810", "9177424331", new BigDecimal(5000));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("9177424331", "9177424331", new BigDecimal(5000));		
	}
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer15() 
	{
		service.fundTransfer("7036071319", "9177424331", new BigDecimal(12000));		
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer16() 
	{
		service.fundTransfer("", "9177424331", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer17() 
	{
		Customer customer=service.fundTransfer("7036071319", "9177424331", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer18() 
	{
		Customer customer=service.fundTransfer("7036071319", "9177424331", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer19() 
	{
		Customer customer=service.fundTransfer("7036071319", "9177424331", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer("", "9177424331", new BigDecimal(-100));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer21() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer22() 
	{
		service.fundTransfer("9177424331", null, new BigDecimal(50));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer23() 
	{
		service.fundTransfer("9177424331", "7702420100", new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount24() throws InsufficientBalanceException, ClassNotFoundException, SQLException 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	
/*	
@Test(expected=InvalidInputException.class)
	public void testDepositAmount25()  
	{
		service.depositAmount("9908015612", new BigDecimal(500));
	}
	*/
	
	
	

	/*
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount26() 
	{
		service.withdrawAmount("7036071319", new BigDecimal(15000));	
	}
	*/

}
