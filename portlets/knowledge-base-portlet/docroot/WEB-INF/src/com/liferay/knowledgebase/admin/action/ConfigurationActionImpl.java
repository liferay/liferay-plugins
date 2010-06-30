/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.admin.action;

import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletConfigFactoryUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("article-added-email")) {
			updateEmailArticleAdded(actionRequest, preferences);
		}
		else if (tabs2.equals("article-updated-email")) {
			updateEmailArticleUpdated(actionRequest, preferences);
		}
		else if (tabs2.equals("display-settings")) {
			updateDisplaySettings(actionRequest, preferences);
		}
		else if (tabs2.equals("email-from")) {
			updateEmailFrom(actionRequest, preferences);
		}
		else if (tabs2.equals("rss")) {
			updateRSS(actionRequest, preferences);
		}

		postProcessPreferences(preferences, actionRequest);

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

		ServletContext servletContext =
			(ServletContext)renderRequest.getAttribute(WebKeys.CTX);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(
			renderRequest, "portletResource");

		Portlet selPortlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletResource);

		PortletConfig selPortletConfig = PortletConfigFactoryUtil.create(
			selPortlet, servletContext);

		String jspPath = selPortletConfig.getInitParameter("jsp-path");

		return jspPath + "configuration.jsp";
	}

	protected void postProcessPreferences(
			PortletPreferences preferences, ActionRequest actionRequest)
		throws Exception {
	}

	protected void updateDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs3 = ParamUtil.getString(actionRequest, "tabs3");

		if (tabs3.equals("article")) {
			updateDisplaySettingsArticle(actionRequest, preferences);
		}
		else if (tabs3.equals("template")) {
			updateDisplaySettingsTemplate(actionRequest, preferences);
		}
	}

	protected void updateDisplaySettingsArticle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int articlesDelta = ParamUtil.getInteger(
			actionRequest, "articlesDelta");
		String articlesDisplayStyle = ParamUtil.getString(
			actionRequest, "articlesDisplayStyle");
		String childArticlesDisplayStyle = ParamUtil.getString(
			actionRequest, "childArticlesDisplayStyle");
		boolean enableArticleDescription = ParamUtil.getBoolean(
			actionRequest, "enableArticleDescription");
		boolean enableArticleComments = ParamUtil.getBoolean(
			actionRequest, "enableArticleComments");
		boolean enableArticleCommentRatings = ParamUtil.getBoolean(
			actionRequest, "enableArticleCommentRatings");

		preferences.setValue("articles-delta", String.valueOf(articlesDelta));
		preferences.setValue("articles-display-style", articlesDisplayStyle);
		preferences.setValue(
			"child-articles-display-style", childArticlesDisplayStyle);
		preferences.setValue(
			"enable-article-description",
			String.valueOf(enableArticleDescription));
		preferences.setValue(
			"enable-article-comments", String.valueOf(enableArticleComments));
		preferences.setValue(
			"enable-article-comment-ratings",
			String.valueOf(enableArticleCommentRatings));
	}

	protected void updateDisplaySettingsTemplate(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int templatesDelta = ParamUtil.getInteger(
			actionRequest, "templatesDelta");
		String templatesDisplayStyle = ParamUtil.getString(
			actionRequest, "templatesDisplayStyle");
		boolean enableTemplateDescription = ParamUtil.getBoolean(
			actionRequest, "enableTemplateDescription");
		boolean enableTemplateComments = ParamUtil.getBoolean(
			actionRequest, "enableTemplateComments");
		boolean enableTemplateCommentRatings = ParamUtil.getBoolean(
			actionRequest, "enableTemplateCommentRatings");

		preferences.setValue("templates-delta", String.valueOf(templatesDelta));
		preferences.setValue("templates-display-style", templatesDisplayStyle);
		preferences.setValue(
			"enable-template-description",
			String.valueOf(enableTemplateDescription));
		preferences.setValue(
			"enable-template-comments", String.valueOf(enableTemplateComments));
		preferences.setValue(
			"enable-template-comment-ratings",
			String.valueOf(enableTemplateCommentRatings));
	}

	protected void updateEmailArticleAdded(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		boolean emailArticleAddedEnabled = ParamUtil.getBoolean(
			actionRequest, "emailArticleAddedEnabled");
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
				"email-article-added-enabled",
				String.valueOf(emailArticleAddedEnabled));
			preferences.setValue(
				"email-article-added-subject", emailArticleAddedSubject);
			preferences.setValue(
				"email-article-added-body", emailArticleAddedBody);
		}
	}

	protected void updateEmailArticleUpdated(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		boolean emailArticleUpdatedEnabled = ParamUtil.getBoolean(
			actionRequest, "emailArticleUpdatedEnabled");
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
				"email-article-updated-enabled",
				String.valueOf(emailArticleUpdatedEnabled));
			preferences.setValue(
				"email-article-updated-subject", emailArticleUpdatedSubject);
			preferences.setValue(
				"email-article-updated-body", emailArticleUpdatedBody);
		}
	}

	protected void updateEmailFrom(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String emailFromName = ParamUtil.getString(
			actionRequest, "emailFromName");
		String emailFromAddress = ParamUtil.getString(
			actionRequest, "emailFromAddress");

		if (Validator.isNull(emailFromName)) {
			SessionErrors.add(actionRequest, "emailFromName");
		}
		else if (!Validator.isEmailAddress(emailFromAddress) &&
				 !Validator.isVariableTerm(emailFromAddress)) {

			SessionErrors.add(actionRequest, "emailFromAddress");
		}
		else {
			preferences.setValue("email-from-name", emailFromName);
			preferences.setValue("email-from-address", emailFromAddress);
		}
	}

	protected void updateRSS(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int rssDelta = ParamUtil.getInteger(actionRequest, "rssDelta");
		String rssDisplayStyle = ParamUtil.getString(
			actionRequest, "rssDisplayStyle");
		String rssFormat = ParamUtil.getString(actionRequest, "rssFormat");

		preferences.setValue("rss-delta", String.valueOf(rssDelta));
		preferences.setValue("rss-display-style", rssDisplayStyle);
		preferences.setValue("rss-format", rssFormat);
	}

}