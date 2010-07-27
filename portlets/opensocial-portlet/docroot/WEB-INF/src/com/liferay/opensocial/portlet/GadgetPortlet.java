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

package com.liferay.opensocial.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.WebKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.UserPref;

/**
 * @author Michael Young
 */
public class GadgetPortlet extends MVCPortlet {

	public static final String PORTLET_NAME_PREFIX = "OPENSOCIAL_";

	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		Portlet portlet = liferayPortletConfig.getPortlet();

		try {
			checkExpando(portlet);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			doRender(renderRequest, renderResponse);

			super.render(renderRequest, renderResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void checkExpando(Portlet portlet) throws Exception {
		long companyId = portlet.getCompanyId();

		if (companyId == CompanyConstants.SYSTEM) {
			return;
		}

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			companyId, User.class.getName(), ShindigUtil.getTableOpenSocial());

		String namespace = PortalUtil.getPortletNamespace(
			portlet.getPortletId());

		String columnName = ShindigUtil.getColumnUserPrefs(namespace);

		try {
			ExpandoColumnLocalServiceUtil.getColumn(
				expandoTable.getTableId(), columnName);
		}
		catch (NoSuchColumnException nsce) {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), columnName,
				ExpandoColumnConstants.STRING);
		}
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		Gadget gadget = getGadget();

		GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget);

		Map<String, UserPref> userPrefs = gadgetSpec.getUserPrefs();

		String portletId = PortalUtil.getPortletId(renderRequest);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletId);

		String urlConfiguration = portletDisplay.getURLConfiguration();

		if ((userPrefs != null) && !userPrefs.isEmpty()) {
			urlConfiguration = urlConfiguration.replaceAll(
				"edit_permissions",
				"edit_configuration");

			portlet.setConfigurationActionClass(_CONFIGURATION_ACTION_CLASS);
		}
		else {
			urlConfiguration = urlConfiguration.replaceAll(
				"edit_configuration",
				"edit_permissions");

			portlet.setConfigurationActionClass(null);
		}

		portletDisplay.setURLConfiguration(urlConfiguration);

		renderRequest.setAttribute(WebKeys.GADGET, gadget);
	}

	protected Gadget getGadget() throws Exception {
		return ShindigUtil.getGadget(getPortletConfig().getPortletName());
	}

	protected String getTitle(RenderRequest renderRequest) {
		try {
			Gadget gadget = getGadget();

			return gadget.getName();
		}
		catch (Exception e) {
			return super.getTitle(renderRequest);
		}
	}

	private final String _CONFIGURATION_ACTION_CLASS =
		"com.liferay.opensocial.portlet.ConfigurationActionImpl";

}