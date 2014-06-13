package com.liferay.jsonwebserviceclient;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class JSONWebServiceUnavailableException extends IOException {

	public JSONWebServiceUnavailableException() {
	}

	public JSONWebServiceUnavailableException(String message) {
		super(message);
	}

	public JSONWebServiceUnavailableException(
		String message, Throwable cause) {
		super(message, cause);
	}

	public JSONWebServiceUnavailableException(Throwable cause) {
		super(cause);
	}

}