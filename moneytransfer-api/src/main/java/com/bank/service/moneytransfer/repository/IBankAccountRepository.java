package com.bank.service.moneytransfer.repository;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankAccountRepository extends CrudRepository<BankAccount, Long> {
}
