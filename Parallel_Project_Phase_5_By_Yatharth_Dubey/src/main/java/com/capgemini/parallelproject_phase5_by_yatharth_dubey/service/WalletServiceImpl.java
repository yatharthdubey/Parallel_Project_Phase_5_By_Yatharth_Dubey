package com.capgemini.parallelproject_phase5_by_yatharth_dubey.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean.Customer;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean.Wallet;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.DuplicateMobileNumberException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.InsufficientAmountException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.exception.MobileNoDoesNotExistException;
import com.capgemini.parallelproject_phase5_by_yatharth_dubey.repo.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepo walletRepo;

	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}

	Customer customer;
	Wallet w;

	@Override
	public Customer createAccount(Customer customerr)
			throws DuplicateMobileNumberException {
		if (walletRepo.existsById(customerr.getMobileNo())) {
			throw new DuplicateMobileNumberException();
		}
		walletRepo.save(customerr);
		return customerr;
	}

	@Override
	public Customer showBalance(String mobileNo) throws MobileNoDoesNotExistException {
		if (walletRepo.findById(mobileNo) == null)
			throw new MobileNoDoesNotExistException();
		System.out.println(walletRepo.findById(mobileNo));
		customer = walletRepo.findById(mobileNo).get();
		return customer;
	}

	@Override
	public Customer[] fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws MobileNoDoesNotExistException, InsufficientAmountException {
		Customer customer1 = new Customer();
		Customer custArray[] = new Customer[2];
		customer1 = walletRepo.findById(sourceMobileNo).get();
		if (customer1 != null) {
			Customer customer2 = new Customer();
			customer2 = walletRepo.findById(targetMobileNo).get();
			if (customer2 != null) {
				custArray[0] = withdrawAmount(sourceMobileNo, amount);
				custArray[0] = depositAmount(targetMobileNo, amount);
			}
		}
		return custArray;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException {
		customer = showBalance(mobileNo);
		w = customer.getWallet();
		w.setBalance(w.getBalance().add(amount));
		customer.setWallet(w);
		walletRepo.save(customer);

		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws MobileNoDoesNotExistException, InsufficientAmountException {

		customer = showBalance(mobileNo);
		w = customer.getWallet();
		if (w.getBalance().compareTo(amount) == -1)
			throw new InsufficientAmountException();
		w.setBalance(w.getBalance().subtract(amount));
		customer.setWallet(w);
		walletRepo.save(customer);

		return customer;
	}

}
