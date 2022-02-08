package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoanInfoService {

	@Autowired
	private LoanInfoRepository loanRepository;
	
	public Mono<LoanInfo> addLoanInfo(LoanInfo loadInfo) {
		return loanRepository.save(loadInfo);
	}

	public Flux<LoanInfo> getAllLoans() {
		return loanRepository.findAll();
	}

	public Mono<LoanInfo> updateLoadInfo(LoanInfo loanInfo) {
		return loanRepository.save(loanInfo);
	}

	public Mono<Void> deleteLoanInfo(String loanId) {
		return loanRepository.deleteById(loanId);
	}
}
