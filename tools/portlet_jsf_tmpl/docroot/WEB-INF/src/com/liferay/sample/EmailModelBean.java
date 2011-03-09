package com.liferay.sample;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EmailModelBean implements Serializable {

	public String getFromEmailAddress() {
		return _fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		_fromEmailAddress = fromEmailAddress;
	}

	public String getRecipientEmailAddress() {
		return _recipientEmailAddress;
	}

	public void setRecipientEmailAddress(String recipientEmailAddress) {
		_recipientEmailAddress = recipientEmailAddress;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public void clear() {
		_fromEmailAddress = null;
		_recipientEmailAddress = null;
		_subject = null;
		_body = null;
	}

	private static final long serialVersionUID = 6491430087812740002L;

	private String _fromEmailAddress;
	private String _recipientEmailAddress;
	private String _subject;
	private String _body;

}
