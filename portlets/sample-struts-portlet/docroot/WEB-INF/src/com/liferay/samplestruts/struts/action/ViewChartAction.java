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

package com.liferay.samplestruts.struts.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.OutputStream;

import java.util.Enumeration;

import javax.portlet.PortletSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewChartAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			if (_log.isInfoEnabled()) {
				_log.info("execute");
			}

			String attrName = "chart_name";

			// Application scoped session attributes can be fetched from the
			// servlet directly. Portlet scoped session attributes can be
			// fetched from Sun's PortletSessionUtil.

			HttpSession session = request.getSession();

			String chartName = (String)session.getAttribute(attrName);
			//(String)_getAttribute(request, attrName);

			// Chart

			String chartType = request.getParameter("chart_type");

			CategoryDataset dataset = _getDataset();

			String xName = "Soda";
			String yName = "Votes";

			JFreeChart chart = null;

			if (chartType.equals("area")) {
				chart = ChartFactory.createAreaChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else if (chartType.equals("horizontal_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset,
					PlotOrientation.HORIZONTAL, true, false, false);
			}
			else if (chartType.equals("line")) {
				chart = ChartFactory.createLineChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else if (chartType.equals("vertical_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else {
				PieDataset pieData = DatasetUtilities.createPieDatasetForRow(
					dataset, 0);

				chart = ChartFactory.createPieChart(
					chartName, pieData, true, false, false);
			}

			response.setContentType("image/jpeg");

			OutputStream out = response.getOutputStream();

			ChartUtilities.writeChartAsJPEG(out, chart, 400, 400);

			return actionMapping.findForward("/common/null.jsp");
		}
		catch (Exception e) {
			request.setAttribute(PageContext.EXCEPTION, e);

			return actionMapping.findForward("/common/error.jsp");
		}
	}

	private Object _getAttribute(HttpServletRequest request, String attrName) {

		// Go through all the session attributes and use Sun's
		// PortletSessionUtil to match the correct attribute name

		HttpSession session = request.getSession();

		Enumeration<String> enu = session.getAttributeNames();

		while (enu.hasMoreElements()) {
			String encodedAttrName = enu.nextElement();

			String decodedAttrName = PortletSessionUtil.decodeAttributeName(
				encodedAttrName);

			if (decodedAttrName.equals(attrName)) {
				return session.getAttribute(encodedAttrName);
			}
		}

		return null;
	}

	private CategoryDataset _getDataset() throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String seriesName = "";

		dataset.addValue(Integer.valueOf(9), seriesName, "Coke");
		dataset.addValue(Integer.valueOf(21), seriesName, "Pepsi");

		return dataset;
	}

	private static Log _log = LogFactoryUtil.getLog(ViewChartAction.class);

}