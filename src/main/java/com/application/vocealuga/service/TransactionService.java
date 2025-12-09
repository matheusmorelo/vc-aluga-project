package com.application.vocealuga.service;

import com.application.vocealuga.dto.TransactionDto;

public interface TransactionService {
    public void saveTransaction(TransactionDto transactionDto);
    public void deleteTransaction(Long id);
    public void updateTransaction();
}
