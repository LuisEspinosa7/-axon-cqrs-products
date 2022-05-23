package com.lsoftware.estore.core.errorHandling;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	
	private final Date timestaDate;
	private final String message;
}
