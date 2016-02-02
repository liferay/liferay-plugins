/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.admin.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;
import com.liferay.wsrp.util.MarkupCharacterSetsUtil;
import com.liferay.wsrp.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerPortletId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerPortletId");

		WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet(
			wsrpConsumerPortletId);
	}

	public void deleteWSRPProducer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpProducerId = ParamUtil.getLong(
			actionRequest, "wsrpProducerId");

		WSRPProducerLocalServiceUtil.deleteWSRPProducer(wsrpProducerId);
	}

	public void restartConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doRestartConsumer(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, "restartConsumer");
		}
	}

	public void updateServiceDescription(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateServiceDescription(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, "updateServiceDescription");
		}
	}

	public void updateWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateWSRPConsumer(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());
		}
	}

	public void updateWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateWSRPConsumerPortlet(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());
		}
	}

	public void updateWSRPConsumerRegistration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateWSRPConsumerRegistration(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());
		}
	}

	public void updateWSRPProducer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateWSRPProducer(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());
		}
	}

	@Override
	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	protected void doRestartConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		WSRPConsumerLocalServiceUtil.restartConsumer(wsrpConsumerId);
	}

	protected void doUpdateServiceDescription(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		WSRPConsumerLocalServiceUtil.updateServiceDescription(wsrpConsumerId);
	}

	protected void doUpdateWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		String adminPortletId = PortalUtil.getPortletId(actionRequest);
		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");
		String forwardCookies = ParamUtil.getString(
			actionRequest, "forwardCookies");
		String forwardHeaders = ParamUtil.getString(
			actionRequest, "forwardHeaders");
		String markupCharacterSets =
			MarkupCharacterSetsUtil.getSupportedMarkupCharacterSets(
				ParamUtil.getString(actionRequest, "markupCharacterSets"));

		if (wsrpConsumerId <= 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				WSRPConsumer.class.getName(), actionRequest);

			WSRPConsumerLocalServiceUtil.addWSRPConsumer(
				themeDisplay.getCompanyId(), adminPortletId, name, url,
				forwardCookies, forwardHeaders, markupCharacterSets,
				serviceContext);
		}
		else {
			WSRPConsumerLocalServiceUtil.updateWSRPConsumer(
				wsrpConsumerId, adminPortletId, name, url, forwardCookies,
				forwardHeaders, markupCharacterSets);
		}
	}

	protected void doUpdateWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerPortletId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerPortletId");

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");
		String name = ParamUtil.getString(actionRequest, "name");
		String portletHandle = ParamUtil.getString(
			actionRequest, "portletHandle");

		if (wsrpConsumerPortletId <= 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				WSRPConsumerPortlet.class.getName(), actionRequest);

			WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
				wsrpConsumerId, name, portletHandle, serviceContext);
		}
		else {
			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
				wsrpConsumerPortletId, name);
		}
	}

	protected void doUpdateWSRPConsumerRegistration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		String adminPortletId = PortalUtil.getPortletId(actionRequest);

		boolean inbandRegistration = ParamUtil.getBoolean(
			actionRequest, "inbandRegistration");

		UnicodeProperties registrationProperties = null;

		if (inbandRegistration) {
			registrationProperties = new UnicodeProperties();

			for (int i = 0;; i++) {
				String registrationPropertyName = ParamUtil.getString(
					actionRequest, "registrationPropertyName" + i);

				String registrationPropertyValue = ParamUtil.getString(
					actionRequest, "registrationPropertyValue" + i);

				if (Validator.isNull(registrationPropertyName)) {
					break;
				}

				registrationProperties.setProperty(
					registrationPropertyName, registrationPropertyValue);
			}
		}

		String registrationHandle = ParamUtil.getString(
			actionRequest, "registrationHandle");

		WSRPConsumerLocalServiceUtil.registerWSRPConsumer(
			wsrpConsumerId, adminPortletId, registrationProperties,
			registrationHandle);
	}

	protected void doUpdateWSRPProducer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long wsrpProducerId = ParamUtil.getLong(
			actionRequest, "wsrpProducerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String version = ParamUtil.getString(actionRequest, "version");
		String portletIds = ParamUtil.getString(actionRequest, "portletIds");

		if (wsrpProducerId <= 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				WSRPProducer.class.getName(), actionRequest);

			WSRPProducerLocalServiceUtil.addWSRPProducer(
				themeDisplay.getUserId(), name, version, portletIds,
				serviceContext);
		}
		else {
			WSRPProducerLocalServiceUtil.updateWSRPProducer(
				wsrpProducerId, name, version, portletIds);
		}
	}

}