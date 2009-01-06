/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.kb.knowledgebase.action;

import com.liferay.kb.util.PortletPropsKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 * @author Peter Shin
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("email-notifications")) {
			updateEmailSettings(actionRequest, preferences);
		}
		else if (tabs2.equals("export-settings")) {
			updateExportSettings(actionRequest, preferences);
		}
		else if (tabs2.equals("rss")) {
			updateRSS(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();

			SessionMessages.add(
				actionRequest, portletConfig.getPortletName() + ".doConfigure");
		}
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/knowledge_base/views/configuration.jsp";
	}

	protected void updateEmailSettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs3 = ParamUtil.getString(actionRequest, "tabs3");

		if (tabs3.equals("general")) {
			updateEmailGeneral(actionRequest, preferences);
		}
		else if (tabs3.equals("article-added-notification")) {
			updateEmailArticleAdded(actionRequest, preferences);
		}
		else if (tabs3.equals("article-updated-notification")) {
			updateEmailArticleUpdated(actionRequest, preferences);
		}
	}

	protected void updateEmailGeneral(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String emailFromName = ParamUtil.getString(
			actionRequest, "emailFromName");
		String emailFromAddress = ParamUtil.getString(
			actionRequest, "emailFromAddress");

		if (Validator.isNull(emailFromName)) {
			SessionErrors.add(actionRequest, "emailFromName");
		}
		else if (!Validator.isEmailAddress(emailFromAddress)) {
			SessionErrors.add(actionRequest, "emailFromAddress");
		}
		else {
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_FROM_NAME, emailFromName);
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_FROM_ADDRESS, emailFromAddress);
		}
	}

	protected void updateEmailArticleAdded(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String emailArticleAddedSubject = ParamUtil.getString(
			actionRequest, "emailArticleAddedSubject");
		String emailArticleAddedBody = ParamUtil.getString(
			actionRequest, "emailArticleAddedBody");

		if (Validator.isNull(emailArticleAddedSubject)) {
			SessionErrors.add(actionRequest, "emailArticleAddedSubject");
		}
		else if (Validator.isNull(emailArticleAddedBody)) {
			SessionErrors.add(actionRequest, "emailArticleAddedBody");
		}
		else {
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_SUBJECT,
				emailArticleAddedSubject);
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_BODY,
				emailArticleAddedBody);
		}
	}

	protected void updateEmailArticleUpdated(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String emailArticleUpdatedSubject = ParamUtil.getString(
			actionRequest, "emailArticleUpdatedSubject");
		String emailArticleUpdatedBody = ParamUtil.getString(
			actionRequest, "emailArticleUpdatedBody");

		if (Validator.isNull(emailArticleUpdatedSubject)) {
			SessionErrors.add(actionRequest, "emailArticleUpdatedSubject");
		}
		else if (Validator.isNull(emailArticleUpdatedBody)) {
			SessionErrors.add(actionRequest, "emailArticleUpdatedBody");
		}
		else {
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_SUBJECT,
				emailArticleUpdatedSubject);
			preferences.setValue(
				PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_BODY,
				emailArticleUpdatedBody);
		}
	}

	protected void updateExportSettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String[] extensions = actionRequest.getParameterValues("extensions");

		preferences.setValues("extensions", extensions);
	}

	protected void updateRSS(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String[] rssTypes = actionRequest.getParameterValues(
			"rssTypes");
		int rssMaxItems = ParamUtil.getInteger(actionRequest, "rssMaxItems");
		String rssDisplayStyle = ParamUtil.getString(
			actionRequest, "rssDisplayStyle");
		int rssAbstractLength = ParamUtil.getInteger(
			actionRequest, "rssAbstractLength");

		preferences.setValues("rss-types", rssTypes);
		preferences.setValue("rss-max-items", String.valueOf(rssMaxItems));
		preferences.setValue("rss-display-style", rssDisplayStyle);
		preferences.setValue(
			"rss-abstract-length", String.valueOf(rssAbstractLength));
	}

}