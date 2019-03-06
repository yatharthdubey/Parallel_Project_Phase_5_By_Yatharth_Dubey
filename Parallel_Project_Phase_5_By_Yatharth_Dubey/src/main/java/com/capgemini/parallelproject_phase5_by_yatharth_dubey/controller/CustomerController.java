package com.capgemini.parallelproject_phase5_by_yatharth_dubey.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean.Customer;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.DuplicateMobileNumberException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.InsufficientAmountException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.MobileNoDoesNotExistException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.service.WalletServiceImpl;

@RestController
public class CustomerController {

	@Autowired
	WalletServiceImpl walletServiceImpl;

	Customer customer;

	@RequestMapping(method = RequestMethod.POST, value = "/addCustomer")
	public Customer createAccount(@RequestBody Customer customer) {

		Customer customerr = null;
		try {
			customerr = walletServiceImpl.createAccount(customer);
		} catch (DuplicateMobileNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerr;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getBalance/{mobileNo}")

	public Customer showBalance(@PathVariable String mobileNo) {

		Customer customer = null;
		try {
			customer = walletServiceImpl.showBalance(mobileNo);
		} catch (MobileNoDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/depositBalance/{mobileNo}/{amount}")

	public Customer depositBalance(@PathVariable String mobileNo, @PathVariable BigDecimal amount){

		Customer customer = null;
		try {
			return walletServiceImpl.depositAmount(mobileNo, amount);
		} catch (MobileNoDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/withdrawBalance/{mobileNo}/{amount}")

	public Customer withdrawBalance(@PathVariable String mobileNo, @PathVariable BigDecimal amount){

		Customer customer = null;
		try {
			customer = walletServiceImpl.withdrawAmount(mobileNo, amount);
		} catch (MobileNoDoesNotExistException e) {
			e.printStackTrace();
		} catch (InsufficientAmountException e) {
			e.printStackTrace();
		}
		return customer;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")

	public Customer[] fundTransfer(@PathVariable String sourceMobileNo, @PathVariable String targetMobileNo,
			@PathVariable BigDecimal amount) {
		
			Customer[] customer = null;
		try {
			customer = walletServiceImpl.fundTransfer(sourceMobileNo, targetMobileNo, amount);
		} catch (MobileNoDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

}
