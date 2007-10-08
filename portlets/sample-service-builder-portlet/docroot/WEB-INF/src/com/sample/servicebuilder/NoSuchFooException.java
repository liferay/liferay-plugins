package com.sample.servicebuilder;

import com.liferay.portal.PortalException;

public class NoSuchFooException extends PortalException {

	public NoSuchFooException() {
		super();
	}

	public NoSuchFooException(String msg) {
		super(msg);
	}

	public NoSuchFooException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchFooException(Throwable cause) {
		super(cause);
	}

}