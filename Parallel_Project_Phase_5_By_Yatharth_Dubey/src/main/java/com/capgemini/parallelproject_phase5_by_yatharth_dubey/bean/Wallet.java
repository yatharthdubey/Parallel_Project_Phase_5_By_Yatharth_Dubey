package com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
public class Wallet {

	@Pattern(regexp="[\\d]+([.][\\d])?([.][\\d][\\d])?")
	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}
}
