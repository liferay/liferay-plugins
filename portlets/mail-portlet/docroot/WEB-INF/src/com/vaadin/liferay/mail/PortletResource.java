package com.vaadin.liferay.mail;

import com.vaadin.terminal.ExternalResource;

/**
 * The equivalent of a theme resource, served automatically by the servlet for
 * the portlet. Resources are typically located in the directories "css" and
 * "images" under the portlet docroot.
 * 
 * @author Henri Sara
 */
public class PortletResource extends ExternalResource {

	public PortletResource(String sourceURL) {
		super("/"+MailPortlet.SERVLET_CONTEXT_NAME+"/" + sourceURL);
	}

}
