package com.adamkorzeniak.catalog.finance.service;

import java.util.List;

import com.adamkorzeniak.catalog.finance.model.Transaction;

public interface TransactionService {

	void create(Transaction transaction);

	List<Transaction> findAllTransactions();

	Transaction findTransaction(long id);

	Transaction updateTransaction(Transaction transaction, Transaction transaction2);

	boolean deleteTransaction(Transaction transaction);

	boolean isTransactionExist(Transaction transaction);

}
