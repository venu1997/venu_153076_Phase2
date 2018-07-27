package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;


public interface WalletService {
public Customer createAccount(String name ,String mobileno, BigDecimal amount);
public Customer showBalance (String mobileno);
public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
public Customer depositAmount (String mobileNo,BigDecimal amount )throws InsufficientBalanceException, ClassNotFoundException, SQLException;;
public Customer withdrawAmount(String mobileNo, BigDecimal amount)throws InsufficientBalanceException, ClassNotFoundException, SQLException;
public List printTransaction(String mobileNo) throws SQLException, ClassNotFoundException; 

}
