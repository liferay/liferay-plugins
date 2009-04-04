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

package com.liferay.portal.bi.reporting.jasper;

import com.liferay.portal.bi.reporting.jasper.compiler.ReportCompiler;
import com.liferay.portal.kernel.bi.reporting.ReportEngine;
import com.liferay.portal.kernel.bi.reporting.ReportFormatExporter;
import com.liferay.portal.kernel.bi.reporting.ReportFormatExporterRegistry;
import com.liferay.portal.kernel.bi.reporting.ReportGenerationException;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="JasperReportEngine.java.html"><b><i>View Source</i></b></a>
 *
 * Implementation of a reporting engine that leverages the Jasper Report Engine.
 *
 * TBD: Need to implement mechanism for registering data sources
 *
 * @author Michael C. Han
 */
public class JasperReportEngine implements ReportEngine {
	public JasperReportEngine(ReportCompiler reportCompiler) {
		_reportCompiler = reportCompiler;
	}

	public synchronized void destroy() {

	}

	public void execute(
		ReportRequest request, ReportResultContainer resultContainer)
		throws ReportGenerationException {
		Connection conn = null;
		try {
			JasperReport report =
				_reportCompiler.compile(request.getRetriever());

			String datasourceName = request.getAlternateDataSource();
			if (Validator.isNull(datasourceName)) {
				conn = _defaultDataSource.getConnection();
			}
			else {
				DataSource src = _customDataSources.get(datasourceName);
				if (src == null) {
					throw new ReportGenerationException(
						"No datasource configured for; " + src);
				}
				conn = src.getConnection();
			}

			JasperPrint print =
				JasperFillManager.fillReport(
					report, request.getReportParameters(), conn);

			ReportFormatExporter exporter =
				_exporters.getExporter(request.getFormat());
			exporter.format(print, request, resultContainer);
		}
		catch (Exception e) {
			throw new ReportGenerationException("Unable to generate report", e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					//nothing to do here...
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to close connection", e);
					}
				}
			}
		}
	}

	public Map<String, String> getEngineParameters() {
		return _engineParameters;
	}

	public synchronized void init(ServletContext context) {

	}

	public void registerDataSource(String name, DataSource source) {
		_customDataSources.put(name, source);
	}

	public void setDefaultDataSouce(DataSource ds) {
		_defaultDataSource = ds;
	}

	public void setEngineParameters(Map<String, String> parameters) {
		_engineParameters = parameters;
	}

	public void setReportFormatExporterRepository(
		ReportFormatExporterRegistry exporters) {
		_exporters = exporters;
	}

	public void unregisterDataSource(String name) {
		_customDataSources.remove(name);
	}

	private static final Log _log = LogFactory.getLog(JasperReportEngine.class);

	private DataSource _defaultDataSource;
	private Map<String, DataSource> _customDataSources =
		new ConcurrentHashMap<String, DataSource>();
	private Map<String, String> _engineParameters;
	private ReportCompiler _reportCompiler;
	private ReportFormatExporterRegistry _exporters;
}
