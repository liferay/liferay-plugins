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

package com.liferay.portal.bi.reporting.jasper.exporter;

import com.liferay.portlet.bi.reporting.ReportFormatExporter;
import com.liferay.portlet.bi.reporting.ReportResultContainer;
import com.liferay.portlet.bi.reporting.ReportGenerationException;
import com.liferay.portlet.bi.reporting.ReportExportException;
import com.liferay.portlet.bi.reporting.ReportRequest;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

/**
 * <a href="PDFReportFormatExporter.java.html"><b><i>View Source</i></b></a>
 *
 * Exports reports in PDF format by leveraging Jasper's HTML Exporter
 *
 * @author Michael C. Han
 */
public class PDFReportFormatExporter implements ReportFormatExporter {

	public void format(
		Object report, ReportRequest request, ReportResultContainer container)
		throws ReportGenerationException {
		try {
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
			exporter.setParameter(
				JRExporterParameter.OUTPUT_STREAM, container.getOutputStream());

			exporter.exportReport();
		}
		catch (Exception e) {
			throw new ReportExportException(
				"Unable to export report in HTML format", e);
		}
	}
}
