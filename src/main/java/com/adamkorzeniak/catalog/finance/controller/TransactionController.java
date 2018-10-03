package com.adamkorzeniak.catalog.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adamkorzeniak.catalog.finance.model.Transaction;
import com.adamkorzeniak.catalog.finance.service.TransactionService;

@RestController
@RequestMapping("/api/finance")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> findTransactions() {

		List<Transaction> transactions = transactionService.findAllTransactions();
		if (transactions.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transaction> findTransaction(@PathVariable long id) {

		Transaction transaction = transactionService.findTransaction(id);
		if (transaction == null) {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction, UriComponentsBuilder ucBuilder) {

		if (transactionService.isTransactionExist(transaction)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		transactionService.create(transaction);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {

		Transaction currentTransaction = transactionService.findTransaction(id);
		if (currentTransaction == null) {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		transactionService.updateTransaction(currentTransaction, transaction);
		return new ResponseEntity<Transaction>(currentTransaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable long id) {

		Transaction transaction = transactionService.findTransaction(id);
		if (transaction == null) {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		transactionService.deleteTransaction(transaction);
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}
}
