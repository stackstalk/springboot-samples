package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class LoanInfoController {

	@Autowired
	private LoanInfoService loanInfoService;
	
	@GetMapping("/loans")
	public Flux<LoanInfo> getLoans() {
		return loanInfoService.getAllLoans().log();
	}
	
	@PostMapping("/loans")
	public Mono<LoanInfo> addLoan(@RequestBody LoanInfo loanInfo) {
		return loanInfoService.addLoanInfo(loanInfo).log();
	}
	
	@PutMapping("/loans")
	public Mono<LoanInfo> updateLoan(@RequestBody LoanInfo loanInfo) {
		return loanInfoService.updateLoadInfo(loanInfo).log();
	}
	
	@DeleteMapping("/loans/{loanId}")
	public Mono<Void> deleteLoan(@PathVariable String loanId) {
		return loanInfoService.deleteLoanInfo(loanId).log();
	}
}
