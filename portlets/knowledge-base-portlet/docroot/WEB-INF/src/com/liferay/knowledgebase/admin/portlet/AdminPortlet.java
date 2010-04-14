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

package com.liferay.knowledgebase.admin.portlet;

import com.liferay.knowledgebase.ArticleContentException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="AdminPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		ArticleServiceUtil.deleteArticle(resourcePrimKey);
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			if (!isProcessActionRequest(actionRequest)) {
				return;
			}

			if (!callActionMethod(actionRequest, actionResponse)) {
				return;
			}

			if (!SessionErrors.isEmpty(actionRequest)) {
				return;
			}

			SessionMessages.add(actionRequest, "request_processed");

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
		catch (PortletException pe) {
			Throwable cause = pe.getCause();

			if (cause instanceof ArticleContentException ||
				cause instanceof ArticleTitleException ||
				cause instanceof NoSuchArticleException ||
				cause instanceof PrincipalException) {

				SessionErrors.add(actionRequest, cause.getClass().getName());
			}
			else {
				throw pe;
			}
		}

		PortalUtil.copyRequestParameters(actionRequest, actionResponse);
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			setRenderRequestAttributes(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void updateArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");
		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		int priority = ParamUtil.getInteger(actionRequest, "priority");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Article.class.getName(), actionRequest);

		if (resourcePrimKey <= 0) {
			ArticleServiceUtil.addArticle(
				parentResourcePrimKey, title, content, description, priority,
				serviceContext);
		}
		else {
			ArticleServiceUtil.updateArticle(
				resourcePrimKey, parentResourcePrimKey, title, content,
				description, priority, serviceContext);
		}
	}

	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String jspPage = processSessionErrors(renderRequest);

		if (jspPage != null) {
			include(jspPage, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected String processSessionErrors(RenderRequest renderRequest) {
		for (String key : SessionErrors.keySet(renderRequest)) {
			if (key.equals(NoSuchArticleException.class.getName()) ||
				key.equals(PrincipalException.class.getName())) {

				return "/admin/error.jsp";
			}
		}

		return null;
	}

	protected void setRenderRequestAttributes(RenderRequest renderRequest)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey");

		Article article = null;

		if (resourcePrimKey > 0) {
			article = ArticleServiceUtil.getLatestArticle(resourcePrimKey);
		}

		renderRequest.setAttribute("KNOWLEDGE_BASE_ARTICLE", article);
	}

}