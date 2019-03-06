package com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="customer_phase5")
public class Customer {
	
	@Id
	@Pattern(regexp="[6-9][0-9]{9}",message="Enter Valid Mobile Number")
	private String mobileNo;
	@Pattern(regexp = "[a-zA-Z ]*",message="Enter Valid Name")
	private String name;
	@Embedded
	private Wallet wallet;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
	}
}
