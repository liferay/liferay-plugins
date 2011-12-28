/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.samplestrutsliferay.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * @author Brian Wing Shun Chan
 */
public class SubscribeForm extends ActionForm {

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest req) {
		_firstName = null;
		_lastName = null;
		_emailAddress = null;
	}

	@Override
	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest req) {

		ActionErrors errors = new ActionErrors();

		if ((_lastName == null) || (_lastName.length() < 1)) {
			errors.add(
				"lastName", new ActionMessage("error.lastName.required"));
		}

		if ((_emailAddress == null) || (_emailAddress.length() < 1)) {
			errors.add(
				"emailAddress",
				new ActionMessage("error.emailAddress.required"));
		}

		return errors;
	}

	@Override
	public String toString() {
		return _firstName + " " + _lastName + " " + _emailAddress;
	}

	private String _firstName;
	private String _lastName;
	private String _emailAddress;

}