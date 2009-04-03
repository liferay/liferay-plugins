/*
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

package com.liferay.portal.bi.reporting.jasper.servlet;

import com.liferay.portlet.bi.reporting.ReportDesignRetriever;
import com.liferay.portlet.bi.reporting.ReportEngine;
import com.liferay.portlet.bi.reporting.ReportGenerationException;
import com.liferay.portlet.bi.reporting.ReportRequest;
import com.liferay.portlet.bi.reporting.ReportResultContainer;
import com.liferay.portlet.bi.reporting.servlet.ServletContextReportDesignRetriever;
import com.liferay.util.bean.PortletBeanLocatorUtil;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="ReportServlet.java.html"><b><i>View Source</i></b></a>
 *
 * THIS IS USED FOR TESTING PURPOSES ONLY
 * 
 * @author Michael C. Han
 */
public class JasperReportServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		_engine =
			(ReportEngine) PortletBeanLocatorUtil.locate(
				"com.liferay.portlet.bi.reports.ReportEngine");
		
		_imageStorageLocation = getServletContext().getRealPath("/images");
	}

	@Override
	protected void service(
		final HttpServletRequest request, final HttpServletResponse response)
		throws ServletException, IOException {
		String reportName = request.getParameter("report");
		String format = request.getParameter("format");
		ReportDesignRetriever retriever =
			new ServletContextReportDesignRetriever(
				getServletContext(), reportName, "/WEB-INF/reports/", ".jrxml");
		ReportRequest reportRequest =
			new ReportRequest(
				retriever, format, _imageStorageLocation,
				request.getContextPath() + "/images");
		ReportResultContainer container =
			new ReportResultContainer() {
				public String getReportName() {
					return null;
				}

				public OutputStream getOutputStream() throws IOException {
					return response.getOutputStream();
				}

				public byte[] getResults() {
					throw new UnsupportedOperationException();
				}

				public ReportResultContainer clone(String name) {
					throw new UnsupportedOperationException();
				}

				public void setReportGenerationException(
					ReportGenerationException e) {
					//To change body of implemented methods use File | Settings | File Templates.
				}

				public boolean hasError() {
					return false;  //To change body of implemented methods use File | Settings | File Templates.
				}

				public ReportGenerationException getReportGenerationException() {
					return null;  //To change body of implemented methods use File | Settings | File Templates.
				}
			};
		try {
			_engine.execute(reportRequest, container);
		}
		catch (ReportGenerationException e) {
			throw new ServletException("Unable to generate report", e);
		}

	}

	private ReportEngine _engine;
	private String _imageStorageLocation;
}
