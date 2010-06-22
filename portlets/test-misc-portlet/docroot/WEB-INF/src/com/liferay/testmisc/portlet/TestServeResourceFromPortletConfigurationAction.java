package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;

import com.liferay.portal.kernel.util.ContentTypes;

import com.liferay.util.servlet.PortletResponseUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * <a href="TestServeResourceFromPortletConfigurationAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alberto Montero
 *
 */
public class TestServeResourceFromPortletConfigurationAction
		extends BaseConfigurationAction {

	public void serveResource(
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		_log.debug(
			"TestServeResourceFromPortletConfigurationAction.serveResource() " +
				"method has been invoked");

		String fileName = "file.csv";
		byte[] bytes = "content".getBytes();
		String contentType = ContentTypes.APPLICATION_TEXT;

		PortletResponseUtil.sendFile(
		  resourceResponse, fileName, bytes, contentType);
	}

	private static Log _log = LogFactoryUtil.getLog(
		TestServeResourceFromPortletConfigurationAction.class);

}
