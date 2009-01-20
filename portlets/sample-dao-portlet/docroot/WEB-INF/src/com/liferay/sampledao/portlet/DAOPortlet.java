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

package com.liferay.sampledao.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.sampledao.model.FoodItem;
import com.liferay.sampledao.model.FoodItemDAO;
import com.liferay.sampledao.util.ConnectionPool;

import java.io.IOException;

import java.sql.SQLException;

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
 * <a href="DAOPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class DAOPortlet extends GenericPortlet {

	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		if (_log.isInfoEnabled()) {
			_log.info("Initializing portlet");
		}
	}

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

				FoodItemDAO.addFoodItem(foodItem);
			}
			else if (cmd.equals(Constants.EDIT)) {
				FoodItem foodItem = FoodItemDAO.getFoodItem(foodItemId);

				foodItem.setName(name);
				foodItem.setPoints(points);

				FoodItemDAO.updateFoodItem(foodItem);
			}
			else if (cmd.equals(Constants.DELETE)) {
				FoodItemDAO.deleteFoodItem(foodItemId);
			}
		}
		catch (SQLException sqle) {
			throw new PortletException(sqle);
		}
	}

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

	public void destroy() {
		if (_log.isInfoEnabled()) {
			_log.info("Destroying portlet");
		}

		try {
			ConnectionPool.destroy();
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DAOPortlet.class);

}