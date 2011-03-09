package com.liferay.sample;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.internet.InternetAddress;

import org.portletfaces.liferay.faces.context.LiferayFacesContext;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.mail.MailEngine;

@ManagedBean
@RequestScoped
public class EmailBackingBean {

	@ManagedProperty(name = "emailModelBean", value = "#{emailModelBean}")
	private EmailModelBean emailModelBean;

	public void setEmailModelBean(EmailModelBean emailModelBean) {
		// Injected via ManagedProperty annotation.
		this.emailModelBean = emailModelBean;
	}

	public void sendMessage() {

		InternetAddress fromInternetAddress;
		try {
			fromInternetAddress = new InternetAddress(
					emailModelBean.getFromEmailAddress());
			InternetAddress recipientInternetAddress = new InternetAddress(
					emailModelBean.getRecipientEmailAddress());
			String subject = emailModelBean.getSubject();
			String body = emailModelBean.getBody();
			boolean htmlFormat = true;
			_liferayFacesContext.addGlobalSuccessInfoMessage();
			_log.debug("Sending email to " + emailModelBean.getRecipientEmailAddress());
			emailModelBean.clear();
			MailEngine.send(fromInternetAddress, recipientInternetAddress,
					subject, body, htmlFormat);
		} catch (Exception e) {
			_log.error(e, e);
			_liferayFacesContext.addGlobalUnexpectedErrorMessage();
		}
	}

	private static final Log _log = LogFactoryUtil
			.getLog(EmailBackingBean.class);

	LiferayFacesContext _liferayFacesContext = LiferayFacesContext
			.getInstance();
}
