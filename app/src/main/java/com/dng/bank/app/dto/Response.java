package com.dng.bank.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	
	private boolean success;
	private Object data;
	private String message;
	private String addInfo;
}
