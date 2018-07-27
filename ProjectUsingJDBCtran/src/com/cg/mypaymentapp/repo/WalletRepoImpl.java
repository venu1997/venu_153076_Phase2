package com.cg.mypaymentapp.repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.*;

public class WalletRepoImpl implements WalletRepo{
   
	   public static Connection getConnection() throws ClassNotFoundException,SQLException{
			Connection con = null;
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		    return con;
	    }

	public boolean save(Customer customer) {
		
	    
		PreparedStatement pstm;
		
		try {
			
			pstm = getConnection().prepareStatement("insert into customer values(?,?,?)");
			pstm.setString(1, customer.getName());
		    pstm.setString(2,customer.getMobileNo() );
		    pstm.setBigDecimal(3, customer.getWallet().getBalance());
		    pstm.execute();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
			
		return true;
		
	}

	public Customer findOne(String mobileNo) 
	{
		Customer cust=null;
		PreparedStatement pstm;
		try {
			
			pstm=getConnection().prepareStatement("select * from customer where mobileno=?");
			pstm.setString(1, mobileNo);
			ResultSet set = pstm.executeQuery();
			while(set.next())
			{
				cust=new Customer();
				cust.setName(set.getString(1));
				cust.setMobileNo(set.getString(2));
				cust.setWallet(new Wallet(set.getBigDecimal("balance")));
				
		}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cust;
		
			
	
		//return customer;
	}

	 public  void saveTransaction(String mobileNo,String statement)throws SQLException, ClassNotFoundException{
		 PreparedStatement pstm;
		 pstm=getConnection().prepareStatement("insert into transaction values(?,?)");
		 pstm.setString(1, mobileNo);
		 pstm.setString(2, statement);
		 pstm.execute();
		 pstm.close();
		 
	 }
	@Override
	public List getTransaction(String mobileNo) throws SQLException, ClassNotFoundException{
		PreparedStatement pstm;
		
			pstm=getConnection().prepareStatement("select statement from transaction where mobileno=?");
		    pstm.setString(1, mobileNo);
		    ResultSet set=pstm.executeQuery();
		    List list=new LinkedList();
		    while(set.next())
		    {
		    	list.add(set.getString(1));
		    }
		  pstm.close();
		  return list;
		
		
		
	}
}
