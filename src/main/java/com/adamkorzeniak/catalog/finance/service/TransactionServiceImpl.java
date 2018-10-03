package com.adamkorzeniak.catalog.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adamkorzeniak.catalog.finance.model.Transaction;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Override
	public void create(Transaction category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction findTransaction(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction, Transaction id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTransaction(Transaction id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTransactionExist(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

}
