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

package com.liferay.portlet.oauth.mvc;

import com.liferay.portal.RequiredFieldException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.service.ApplicationLocalServiceUtil;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.net.MalformedURLException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 *
 * @author Igor Beslic
 *
 */
public class AdminPortlet extends MVCPortlet {

	public void addApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		String name = ParamUtil.getString(actionRequest, OAuthConstants.NAME);
		String description = ParamUtil.getString(
			actionRequest, OAuthConstants.DESCRIPTION);
		String website = ParamUtil.getString(
			actionRequest, OAuthConstants.WEBSITE);
		String callBackURL = ParamUtil.getString(
			actionRequest, OAuthConstants.CALLBACK_URL);
		int accessLevel = ParamUtil.getInteger(
			actionRequest, OAuthConstants.ACCESS_TYPE,
			OAuthConstants.ACCESS_TYPE_READ);

		try {

			// TODO always use a ServiceContext with adds and updates.

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			Application application =
				ApplicationLocalServiceUtil.addApplication(
					serviceContext.getUserId(), name, description, website,
					callBackURL, accessLevel, serviceContext);

			// TODO this won't work see above

			actionRequest.setAttribute(OAuthConstants.BEAN_ID, application);
		}
		catch (Exception e) {
			if (e instanceof SystemException) {
				_log.error(e, e);
			}
			else if (e instanceof RequiredFieldException ||
					 e instanceof MalformedURLException) {

				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
	}

	public void deleteApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
				actionRequest, OAuthConstants.APPLICATION_ID);

		try {
			if (applicationId > 0) {
				ServiceContext serviceContext =
						ServiceContextFactory.getInstance(actionRequest);

				ApplicationLocalServiceUtil.deleteApplication(
					applicationId, serviceContext);
			}
			else {
				SessionErrors.add(
					actionRequest,
					"can-not-complete-operation-without-application-id");
			}
		}
		catch (Exception e) {
			if (e instanceof SystemException) {
				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renberResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
			renderRequest, OAuthConstants.APPLICATION_ID);

		if (applicationId > 0) {
			try {
				Application application =
					ApplicationLocalServiceUtil.fetchApplication(applicationId);

				renderRequest.setAttribute(OAuthConstants.BEAN_ID, application);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(renderRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
		}

		super.render(renderRequest, renberResponse);
	}

	public void updateApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long applicationId = ParamUtil.getLong(
			actionRequest, OAuthConstants.APPLICATION_ID);

		if (applicationId > 0) {
			String name = ParamUtil.getString(
				actionRequest, OAuthConstants.NAME);
			String description = ParamUtil.getString(
				actionRequest, OAuthConstants.DESCRIPTION);
			String website = ParamUtil.getString(
				actionRequest, OAuthConstants.WEBSITE);
			String callBackURL = ParamUtil.getString(
				actionRequest, OAuthConstants.CALLBACK_URL);

			try {
				Application application =
					ApplicationLocalServiceUtil.fetchApplication(applicationId);

				ServiceContext serviceContext =
						ServiceContextFactory.getInstance(actionRequest);

				application = ApplicationLocalServiceUtil
					.updateApplication(
						serviceContext.getUserId(), applicationId, name,
						description, website, callBackURL, serviceContext);

				actionRequest.setAttribute(OAuthConstants.BEAN_ID, application);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(actionRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e);
				}
			}
		}
		else {
			SessionErrors.add(
				actionRequest,
				"can-not-complete-operation-without-application-id");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}