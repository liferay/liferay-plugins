/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ParamUtil;
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
import javax.portlet.WindowState;

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

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletDisplay.getId());

		Gadget gadget = getGadget();

		GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

		String urlConfiguration = portletDisplay.getURLConfiguration();

		if (hasUserPrefs(gadgetSpec)) {
			portlet.setConfigurationActionClass(
				ConfigurationActionImpl.class.getName());

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_permissions", "edit_configuration");
		}
		else {
			portlet.setConfigurationActionClass(null);

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_configuration", "edit_permissions");
		}

		portletDisplay.setURLConfiguration(urlConfiguration);

		renderRequest.setAttribute(WebKeys.GADGET, gadget);

		String view = getView(renderRequest, gadgetSpec);

		renderRequest.setAttribute(WebKeys.VIEW, view);
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

	protected String getView(
		RenderRequest renderRequest, GadgetSpec gadgetSpec) {

		WindowState windowState = renderRequest.getWindowState();

		String view = ParamUtil.getString(renderRequest, "view");

		if (gadgetSpec.getView(view) != null) {
			return view;
		}

		if (windowState.equals(WindowState.NORMAL)) {
			if (gadgetSpec.getView("default") != null) {
				view = "default";
			}
			else if (gadgetSpec.getView("home") != null) {
				view = "home";
			}
			else if (gadgetSpec.getView("profile") != null) {
				view = "profile";
			}
			else if (gadgetSpec.getView("canvas") != null) {
				view = "canvas";
			}
		}
		else if (windowState.equals(WindowState.MAXIMIZED)) {
			if (gadgetSpec.getView("canvas") != null) {
				view = "canvas";
			}
			else if (gadgetSpec.getView("default") != null) {
				view = "default";
			}
			else if (gadgetSpec.getView("home") != null) {
				view = "home";
			}
			else if (gadgetSpec.getView("profile") != null) {
				view = "profile";
			}
		}

		return view;
	}

	protected boolean hasUserPrefs(GadgetSpec gadgetSpec) throws Exception {
		Map<String, UserPref> userPrefs = gadgetSpec.getUserPrefs();

		if ((userPrefs != null) && !userPrefs.isEmpty()) {
			for (UserPref userPref : userPrefs.values()) {
				if (userPref.getDataType() != UserPref.DataType.HIDDEN) {
					return true;
				}
			}
		}

		return false;
	}

}