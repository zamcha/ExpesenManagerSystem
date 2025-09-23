package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.TransactionCreateRequest;
import com.vti.entity.Transaction;
import com.vti.form.TransactionFilterForm;

public interface ITransactionService {

	void createTransaction(TransactionCreateRequest form);
	Transaction getTransaction(Long id);
	void deleteTransaction(Long id);
	Page<Transaction> getAllTransaction(Pageable pageable, String search, TransactionFilterForm filterForm);
}
