package com.stackstalk;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LoanInfoRepository extends ReactiveMongoRepository<LoanInfo, String> {

}
