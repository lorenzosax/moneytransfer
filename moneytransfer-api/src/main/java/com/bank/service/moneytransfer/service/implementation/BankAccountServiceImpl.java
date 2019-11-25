package com.bank.service.moneytransfer.service.implementation;

import java.math.BigDecimal;
import javax.validation.Valid;

import com.bank.service.moneytransfer.controller.CustomerController;
import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.pojo.BankOperationEnum;
import com.bank.service.moneytransfer.repository.IBankAccountRepository;
import com.bank.service.moneytransfer.service.IBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BankAccountServiceImpl implements IBankAccountService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private IBankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(IBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public BankAccount updateAvailableBalance(@Valid BankAccount bankAccount, BankOperationEnum bankOperation,
                                              BigDecimal amountToTransfer) {
        BigDecimal oldAvailableBalance = bankAccount.getAvailableBalance();
        switch (bankOperation) {
            case SEND_MONEY:
                bankAccount.setAvailableBalance(oldAvailableBalance.subtract(amountToTransfer));
                logger.info("SEND_MONEY: Update available balance, subtract " + amountToTransfer);
                break;
            case RECEIVE_MONEY:
                bankAccount.setAvailableBalance(oldAvailableBalance.add(amountToTransfer));
                logger.info("RECEIVE_MONEY: Update available balance, add " + amountToTransfer);
                break;
            default:
                logger.error("bankOperation not Allowed");
        }
        return bankAccountRepository.save(bankAccount);
    }
}
