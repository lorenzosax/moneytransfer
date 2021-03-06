package com.bank.service.moneytransfer.repository;

import java.util.Optional;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.entity.Customer;
import com.bank.service.moneytransfer.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends CrudRepository<Transaction, Long> {

    public Optional<Transaction> findTransactionByTrxIdAndCustomerAndBankAccount(String trxId, Customer customerId,
                                                                                 BankAccount bankAccount);
}
