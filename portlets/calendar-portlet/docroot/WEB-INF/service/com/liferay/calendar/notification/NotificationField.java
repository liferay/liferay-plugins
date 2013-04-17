package com.liferay.calendar.notification;

public enum NotificationField {
	BODY("body"), SUBJECT("subject");

	public static NotificationField parse(String value) {
		if (BODY.getValue().equals(value)) {
			return BODY;
		}
		else if (SUBJECT.getValue().equals(value)) {
			return SUBJECT;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private NotificationField(String value) {
		_value = value;
	}

	private String _value;
}
