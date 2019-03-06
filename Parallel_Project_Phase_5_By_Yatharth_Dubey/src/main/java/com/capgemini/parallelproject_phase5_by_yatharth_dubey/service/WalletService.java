package com.capgemini.parallelproject_phase5_by_yatharth_dubey.service;

import java.math.BigDecimal;

import com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean.Customer;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.DuplicateMobileNumberException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.InsufficientAmountException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.MobileNoDoesNotExistException;

public interface WalletService {

	public Customer createAccount(Customer customer) throws DuplicateMobileNumberException;
	
	
	public Object showBalance(String mobileNo) throws MobileNoDoesNotExistException;
	
	
	public Customer[] fundTransfer(String sourceMobileNo,String targetMobileNo,BigDecimal amount) throws MobileNoDoesNotExistException,InsufficientAmountException;
	
	
	public Customer depositAmount(String mobileNo,BigDecimal amount) throws MobileNoDoesNotExistException;
	
	
	public Customer withdrawAmount(String mobileNo,BigDecimal amount)throws MobileNoDoesNotExistException,InsufficientAmountException;
	
	
	
}
