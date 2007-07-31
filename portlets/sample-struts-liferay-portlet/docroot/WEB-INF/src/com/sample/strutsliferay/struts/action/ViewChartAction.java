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

package com.sample.strutsliferay.struts.action;

import java.io.OutputStream;

import java.util.Enumeration;

import javax.portlet.PortletSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * <a href="ViewChartAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ViewChartAction extends Action {

	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse res)
		throws Exception {

		try {
			if (_log.isInfoEnabled()) {
				_log.info("execute");
			}

			String portletName = "sample_struts_liferay_portlet";
			String attrName = "chart_name";

			// Application scoped session attributes can be fetched from the
			// servlet directly. Portlet scoped session attributes can be
			// fetched from Sun's PortletSessionUtil.

			HttpSession ses = req.getSession();

			String chartName =
				(String)ses.getAttribute(attrName);
				//(String)_getAttribute(req, attrName);

			// Chart

			String chartType = req.getParameter("chart_type");

			CategoryDataset dataset = _getDataset();

			String xName = "Soda";
			String yName = "Votes";

			JFreeChart chart = null;

			if (chartType.equals("area")) {
				chart = ChartFactory.createAreaChart(
					chartName, xName, yName, dataset,
					PlotOrientation.VERTICAL, true, false, false);
			}
			else if (chartType.equals("horizontal_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset,
					PlotOrientation.HORIZONTAL, true, false, false);
			}
			else if (chartType.equals("line")) {
				chart = ChartFactory.createLineChart(
					chartName, xName, yName, dataset,
					PlotOrientation.VERTICAL, true, false, false);
			}
			else if (chartType.equals("vertical_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset,
					PlotOrientation.VERTICAL, true, false, false);
			}
			else {
				PieDataset pieData =
					DatasetUtilities.createPieDatasetForRow(dataset, 0);

				chart = ChartFactory.createPieChart(
					chartName, pieData, true, false, false);
			}

			res.setContentType("image/jpeg");

			OutputStream out = res.getOutputStream();
			ChartUtilities.writeChartAsJPEG(out, chart, 400, 400);

			return mapping.findForward("/common/null.jsp");
		}
		catch (Exception e) {
			req.setAttribute(PageContext.EXCEPTION, e);

			return mapping.findForward("/common/error.jsp");
		}
	}

	private Object _getAttribute(HttpServletRequest req, String attrName) {

		// Go through all the session attributes and use Sun's
		// PortletSessionUtil to match the correct attribute name

		HttpSession ses = req.getSession();

		Enumeration enu = ses.getAttributeNames();

		while (enu.hasMoreElements()) {
			String encodedAttrName = (String)enu.nextElement();

			String decodedAttrName =
				PortletSessionUtil.decodeAttributeName(encodedAttrName);

			if (decodedAttrName.equals(attrName)) {
				return ses.getAttribute(encodedAttrName);
			}
		}

		return null;
	}

	private CategoryDataset _getDataset() throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String seriesName = "";

		dataset.addValue(new Integer(9), seriesName, "Coke");
		dataset.addValue(new Integer(21), seriesName, "Pepsi");

		return dataset;
	}

	private static Log _log = LogFactory.getLog(ViewChartAction.class);

}