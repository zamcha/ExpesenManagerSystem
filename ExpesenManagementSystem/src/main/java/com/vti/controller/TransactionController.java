package com.vti.controller;

import com.vti.dto.TransactionCreateRequest;
import com.vti.dto.TransactionResponse;
import com.vti.entity.Transaction;
import com.vti.form.TransactionFilterForm;
import com.vti.service.ITransactionService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;
    
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionCreateRequest form) {
    	transactionService.createTransaction(form);
        return ResponseEntity.ok("Tạo mới thành công");
    }

    @GetMapping
    public Page<TransactionResponse> getAllTransactions(Pageable pageable,@RequestParam(value="search", required = false)String search, TransactionFilterForm filterForm) {
    	Page<Transaction> entityPages = transactionService.getAllTransaction(pageable, search, filterForm);
    	
    	List<TransactionResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<TransactionResponse>>() {
		}.getType());
    	
    	Page<TransactionResponse> dtosPage = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
        return dtosPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}