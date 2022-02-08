package com.stackstalk;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document
public class LoanInfo {

	@Id
	private String loanId;
	private String loanType;
	private Float interestRate;
	private Integer maxTermInMonths;
	private Float processingFee;
}
