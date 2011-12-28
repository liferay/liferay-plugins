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

package com.liferay.samplehibernate.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.samplehibernate.model.FoodItem;
import com.liferay.samplehibernate.util.FoodItemUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class HibernatePortlet extends GenericPortlet {

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		if (_log.isInfoEnabled()) {
			_log.info("Initializing portlet");
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long foodItemId = ParamUtil.getLong(actionRequest, "foodItemId");

		String name = actionRequest.getParameter("name");
		int points = ParamUtil.getInteger(actionRequest, "points");

		try {
			if (cmd.equals(Constants.ADD)) {
				FoodItem foodItem = new FoodItem();

				foodItem.setName(name);
				foodItem.setPoints(points);

				FoodItemUtil.addFoodItem(foodItem);
			}
			else if (cmd.equals(Constants.EDIT)) {
				FoodItem foodItem = FoodItemUtil.getFoodItem(foodItemId);

				foodItem.setName(name);
				foodItem.setPoints(points);

				FoodItemUtil.updateFoodItem(foodItem);
			}
			else if (cmd.equals(Constants.DELETE)) {
				FoodItemUtil.deleteFoodItem(foodItemId);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletContext portletContext = getPortletContext();

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher("/view.jsp");

		if (portletRequestDispatcher == null) {
			_log.error("/view.jsp is not a valid include");
		}
		else {
			try {
				portletRequestDispatcher.include(renderRequest, renderResponse);
			}
			catch (Exception e) {
				_log.error(e, e);

				portletRequestDispatcher = portletContext.getRequestDispatcher(
					"/error.jsp");

				if (portletRequestDispatcher == null) {
					_log.error("/error.jsp is not a valid include");
				}
				else {
					portletRequestDispatcher.include(
						renderRequest, renderResponse);
				}
			}
		}
	}

	@Override
	public void destroy() {
		if (_log.isInfoEnabled()) {
			_log.info("Destroying portlet");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(HibernatePortlet.class);

}