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

package com.liferay.opensocial.gadget.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.WebKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.apache.shindig.gadgets.spec.GadgetSpec;

/**
 * @author Michael Young
 */
public abstract class BaseGadgetPortlet extends MVCPortlet {

	public static final String PORTLET_NAME_PREFIX = "OPENSOCIAL_";

	@Override
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

	protected void checkExpando(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			themeDisplay.getCompanyId(), Layout.class.getName(),
			ShindigUtil.getTableOpenSocial());

		String namespace = renderResponse.getNamespace();

		String columnName = ShindigUtil.getColumnUserPrefs(
			namespace, themeDisplay);

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			expandoTable.getTableId(), columnName);

		if (expandoColumn == null) {
			expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), columnName,
				ExpandoColumnConstants.STRING);

			Role role = RoleLocalServiceUtil.getRole(
				expandoColumn.getCompanyId(), RoleConstants.USER);

			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				expandoColumn.getCompanyId(), ExpandoColumn.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(expandoColumn.getColumnId()), role.getRoleId(),
				new String[] {ActionKeys.UPDATE, ActionKeys.VIEW});
		}
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		checkExpando(renderRequest, renderResponse);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletDisplay.getId());

		Gadget gadget = getGadget(renderRequest);

		if (gadget == null) {
			return;
		}

		GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

		overrideConfiguration(gadgetSpec, portlet, portletDisplay);

		renderRequest.setAttribute(WebKeys.GADGET, gadget);

		String view = getView(renderRequest, gadgetSpec);

		renderRequest.setAttribute(WebKeys.VIEW, view);
	}

	protected abstract Gadget getGadget(RenderRequest renderRequest)
		throws Exception;

	@Override
	protected String getTitle(RenderRequest renderRequest) {
		Gadget gadget = null;

		try {
			gadget = getGadget(renderRequest);
		}
		catch (Exception e) {
		}

		if (gadget != null) {
			return gadget.getName();
		}
		else {
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

	protected void overrideConfiguration(
			GadgetSpec gadgetSpec, Portlet portlet,
			PortletDisplay portletDisplay)
		throws Exception {
	}

}