
package com.liferay.bi.report.util;

import javax.servlet.ServletContext;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.servlet.ServletContextReportDesignRetriever;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;

public class ReportUtil {

	public static final String REPORT_REQUEST = "liferay/report_request";

	public static ReportRequest getReportRequest(
		ServletContext servletContext, ReportDefinition deportDefinition)
		throws PortalException, SystemException {

		String[] existingAttachments =
			DLServiceUtil.getFileNames(
				deportDefinition.getCompanyId(), CompanyConstants.SYSTEM,
				deportDefinition.getAttachmentsDir());

		ReportDesignRetriever reportDesignRetriever =
			new ServletContextReportDesignRetriever(
				servletContext, existingAttachments[0],
				deportDefinition.getAttachmentsDir(), StringPool.BLANK);

		//TBD 
		ReportRequest request =
			new ReportRequest(
				reportDesignRetriever, deportDefinition.getReportFormat(), "/", "/");
		return request;

	}
}
