package com.cg.mypaymentapp.repo;

import java.sql.SQLException;
import java.util.List;

import com.cg.mypaymentapp.beans.Customer;

public interface WalletRepo {

public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);

	public List getTransaction(String mobileNo) throws SQLException,ClassNotFoundException;

	public void saveTransaction(String mobileNo,String statement)throws SQLException, ClassNotFoundException;
}
