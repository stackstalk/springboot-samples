package com.stackstalk;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LogInfoRepository extends ReactiveMongoRepository<LogInfo, String> {

}
