package com.taxation.entity;

public class ApplicationResponse {
	private Object data;
	private boolean status;
	private String message;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApplicationResponse(Object data, boolean status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}

}
