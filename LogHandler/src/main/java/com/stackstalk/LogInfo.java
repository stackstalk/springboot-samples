package com.stackstalk;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document
public class LogInfo {
	@Id 
	private String logId;
	private Long logTimestamp;
	private String logMessage;
}