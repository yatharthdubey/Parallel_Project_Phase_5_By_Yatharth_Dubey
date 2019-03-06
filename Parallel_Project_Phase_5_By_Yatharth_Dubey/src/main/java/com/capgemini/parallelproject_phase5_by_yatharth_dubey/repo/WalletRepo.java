package com.capgemini.parallelproject_phase5_by_yatharth_dubey.repo;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.parallelproject_phase5_by_yatharth_dubey.bean.Customer;

public interface WalletRepo extends CrudRepository<Customer, String> {

}