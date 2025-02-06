package com.hms.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String message;
	private LocalDateTime timestamp;
	    public ErrorResponse() {
			// TODO Auto-generated constructor stub
		}
	    public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "ErrorResponse [message=" + message + ", timestamp=" + timestamp + "]";
		}
}
