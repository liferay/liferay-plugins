/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.dao.portlet;

import com.sample.dao.model.FoodItem;
import com.sample.dao.model.FoodItemDAO;
import com.sample.dao.util.ConnectionPool;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="DAOPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class DAOPortlet extends GenericPortlet {

	public void init(PortletConfig config) throws PortletException {
		super.init(config);

		if (_log.isInfoEnabled()) {
			_log.info("Initializing portlet");
		}
	}

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		String command = req.getParameter("command");

		long foodItemId = 0;

		try {
			foodItemId = Long.parseLong(req.getParameter("foodItemId"));
		}
		catch (Exception e) {
		}

		String name = req.getParameter("name");

		int points = 0;

		try {
			points = Integer.parseInt(req.getParameter("points"));
		}
		catch (Exception e) {
		}

		try {
			if (command.equals("add")) {
				FoodItem foodItem = new FoodItem();

				foodItem.setName(name);
				foodItem.setPoints(points);

				FoodItemDAO.addFoodItem(foodItem);
			}
			else if (command.equals("edit")) {
				FoodItem foodItem = FoodItemDAO.getFoodItem(foodItemId);

				foodItem.setName(name);
				foodItem.setPoints(points);

				FoodItemDAO.updateFoodItem(foodItem);
			}
			else if (command.equals("delete")) {
				FoodItemDAO.deleteFoodItem(foodItemId);
			}
		}
		catch (SQLException sqle) {
			throw new PortletException(sqle);
		}
	}

	public void doView(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		PortletContext ctx = getPortletContext();

		PortletRequestDispatcher prd = ctx.getRequestDispatcher("/view.jsp");

		if (prd == null) {
			_log.error("/view.jsp is not a valid include");
		}
		else {
			try {
				prd.include(req, res);
			}
			catch (Exception e) {
				prd = ctx.getRequestDispatcher("/error.jsp");

				if (prd == null) {
					_log.error("/error.jsp is not a valid include");
				}
				else {
					prd.include(req, res);
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

	private static Log _log = LogFactory.getLog(DAOPortlet.class);

}