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

package com.liferay.sampleicefaces.managedbean;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsf.common.ActionOutcomes;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;
import com.liferay.util.bridges.jsf.common.JSFPortletUtil;
import com.liferay.util.bridges.jsf.common.SelectItemList;
import com.liferay.util.bridges.jsf.common.comparator.SelectItemComparator;
import com.liferay.util.mail.MailEngine;

import java.io.File;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

/**
 * <p>
 * This class is the backing bean behind the JobApplication.jspx file. It
 * contains bean properties to store the values of each ICEfaces component on
 * the page. In addition, it contains a submit action callback, so that the job
 * application form can be sent in the form of an email.
 * </p>
 *
 * <p>
 * Note that the email will only be sent if the portlet is configured to work
 * with your mail server as defined in the META-INF/context.xml file or
 * $JBOSS_HOME/server/default/deploy/mail-service.xml file. Also, the file
 * upload directory is defined in the WEB-INF/web.xml file.
 * </p>
 *
 * @author Neil Griffin
 */
public class JobApplication {

	public JobApplication() {

		// Store the portlet preferences as a bean property because of ICE-1625.
		// When using normal JSF, this constructor will get called each time a
		// request is made. This is a little inefficient, but it's a coding
		// tradeoff to make things work with both normal JSF and ICEfaces 1.6.0.

		_portletPreferences = JSFPortletUtil.getPortletPreferences(
			FacesContext.getCurrentInstance());
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public Date getDateOfBirth() {
		return _dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		_dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getFaxNumber() {
		return _faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		_faxNumber = faxNumber;
	}

	public File getFile() {
		return _file;
	}

	public void setFile(File file) {
		_file = file;
	}

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

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getPhoneNumberType() {
		return _phoneNumberType;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		_phoneNumberType = phoneNumberType;
	}

	public SelectItemList getPhoneNumberTypeSelectItems() {
		if (_phoneNumberTypeSelectItems == null) {
			_phoneNumberTypeSelectItems = new SelectItemList();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			Locale locale = JSFPortletUtil.getLocale(facesContext);

			_phoneNumberTypeSelectItems.add(
				new SelectItem("work", LanguageUtil.get(locale, "work-phone")));
			_phoneNumberTypeSelectItems.add(
				new SelectItem("home", LanguageUtil.get(locale, "home-phone")));
			_phoneNumberTypeSelectItems.add(
				new SelectItem(
					"mobile", LanguageUtil.get(locale, "mobile-phone")));

			Collections.sort(
				_phoneNumberTypeSelectItems, new SelectItemComparator());

			_phoneNumberTypeSelectItems.prependEmptySelectItem(facesContext);
		}

		return _phoneNumberTypeSelectItems;
	}

	public String submit() {
		String actionOutcome = ActionOutcomes.SUCCESS;

		FacesContext facesContext = FacesContext.getCurrentInstance();

		String toEmailAddress = _portletPreferences.getValue(
			"recipientEmailAddress", null);

		try {
			InternetAddress fromInternetAddress = new InternetAddress(
				_emailAddress);
			InternetAddress[] toInternetAddresses =
				new InternetAddress[] {new InternetAddress(toEmailAddress)};

			String subject =
				_firstName + " " + _lastName + " has applied for a job";

			StringBuffer body = new StringBuffer();

			body.append("First Name: " + _firstName + "\n");
			body.append("Last Name: " + _lastName + "\n");
			body.append("Date Of Birth: " + _dateOfBirth + "\n");
			body.append("Phone Number: " + _phoneNumber + "\n");
			body.append("Phone Type: " + _phoneNumberType + "\n");
			body.append("Fax Number: " + _faxNumber + "\n");
			body.append("Comments:\n");
			body.append(_comments);

			if (_log.isDebugEnabled()) {
				_log.debug("Sending email to " + toEmailAddress);
			}

			MailEngine.send(
				fromInternetAddress, toInternetAddresses, null, null, null,
				subject, body.toString(), false, null, null, null, null);

			FacesMessageUtil.info(
				facesContext,
				"thank-you-for-applying-for-a-job-with-our-organization",
				_firstName);

			clearModel();
		}
		catch (Exception e) {
			FacesMessageUtil.error(
				facesContext,
				"an-unexpected-error-occurred-while-sending-your-message");

			if (_log.isErrorEnabled()) {
				_log.error(e.getMessage(), e);
			}

			actionOutcome = ActionOutcomes.FAILURE;
		}

		return actionOutcome;
	}

	protected void clearModel() {
		_comments = null;
		_dateOfBirth = null;
		_emailAddress = null;
		_faxNumber = null;
		_file = null;
		_firstName = null;
		_lastName = null;
		_phoneNumber = null;
		_phoneNumberType = null;
	}

	private static Log _log = LogFactoryUtil.getLog(JobApplication.class);

	private String _comments;
	private Date _dateOfBirth;
	private String _emailAddress;
	private String _faxNumber;
	private File _file;
	private String _firstName;
	private String _lastName;
	private String _phoneNumber;
	private String _phoneNumberType;
	private SelectItemList _phoneNumberTypeSelectItems;
	private PortletPreferences _portletPreferences;

}