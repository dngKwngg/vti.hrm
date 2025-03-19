package com.example.vti.exceptions;

import java.util.Map;

public record ErrorResponse(String message,
							String detailMessage,
							Map<String, String> errors,
							int code,
							String moreInformation) {

}
