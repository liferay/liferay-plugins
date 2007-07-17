/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.sample.icefaces.managedbean;

import com.liferay.portal.kernel.language.LanguageUtil;

import com.liferay.util.bridges.jsf.common.ActionOutcomes;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;
import com.liferay.util.bridges.jsf.common.JSFPortletUtil;
import com.liferay.util.bridges.jsf.common.SelectItemList;
import com.liferay.util.bridges.jsf.common.comparator.SelectItemComparator;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

/**
 * <a href="Contact.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This class is the backing bean behind the JobApplication.jspx file. It
 * contains bean properties to store the values of each ICEfaces component
 * on the page. In addition, it contains a submit action callback, so that
 * the job application form can be sent in the form of an email.
 * </p>
 *
 * @author Neil Griffin
 *
 */
public class JobApplication {

	public JobApplication() {

		// Because of http://jira.icefaces.org/browse/ICE-1625
		// it is necessary to keep the PortletPreferences stored
		// in a bean property. When using normal JSF, this constructor
		// will get called each time a request is made. This is a little
		// inefficient, but it's a coding-tradeoff to make things work
		// with both normal JSF and ICEfaces 1.6.
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
		return (_file);
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

		return (_phoneNumberTypeSelectItems);
	}

	public String submit() {

		String returnValue = ActionOutcomes.SUCCESS;
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String toEmailAddress = _portletPreferences.getValue(
				"recipientEmailAddress", null);

		try {

			// Send an email message to the human resources dept.
			InternetAddress fromInternetAddress = new InternetAddress(
					_emailAddress);
			InternetAddress[] toInternetAddresses = new InternetAddress[] {
					new InternetAddress(toEmailAddress)
				};
			String subject = _firstName + " " + _lastName +
				" has applied for a job";
			StringBuffer body = new StringBuffer();
			body.append("First Name:    " + _firstName + "\n");
			body.append("Last Name:     " + _lastName + "\n");
			body.append("Date Of Birth: " + _dateOfBirth + "\n");
			body.append("Phone Number:  " + _phoneNumber + "\n");
			body.append("Phone Type:    " + _phoneNumberType + "\n");
			body.append("Fax Number:    " + _faxNumber + "\n");
			body.append("Comments:\n");
			body.append(_comments);

			if (_log.isDebugEnabled()) {
				_log.debug("Sending email to: " + toEmailAddress);
			}

			MailEngine.send(
				fromInternetAddress, toInternetAddresses, null, null, subject,
				body.toString(), false, null, null, null, new File[] {_file});

			// Ask ICEfaces to display an informational message, letting
			// the user know that the email message was sent.
			FacesMessageUtil.addGlobalMessageFromLiferay(
				facesContext, FacesMessage.SEVERITY_INFO,
				"thank-you-for-applying-for-a-job",
				_firstName);

			// Clear the values in the model, so that ICEfaces will re-render
			// the portlet with empty fields.
			_clearModel();
		}
		catch (Exception exception) {

			// Ask ICEfaces to display an error message, letting the user know
			// that the email message was not sent.
			FacesMessageUtil.addGlobalMessageFromLiferay(
				facesContext, FacesMessage.SEVERITY_ERROR,
				"an-unexpected-error-occurred-while-sending-your-message");

			if (_log.isErrorEnabled()) {
				_log.error(exception.getMessage(), exception);
			}
		}

		return (returnValue);
	}

	private void _clearModel() {

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

	private static Log _log = LogFactory.getLog(JobApplication.class);
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
