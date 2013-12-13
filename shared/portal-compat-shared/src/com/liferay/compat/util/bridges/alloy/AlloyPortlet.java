/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.Router;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;

import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloyPortlet extends GenericPortlet {

	@Override
	public void destroy() {
		for (AlloyController alloyController : _alloyControllers) {
			BaseAlloyControllerImpl baseAlloyControllerImpl =
				(BaseAlloyControllerImpl)alloyController;

			Indexer indexer = baseAlloyControllerImpl.indexer;

			if (indexer != null) {
				IndexerRegistryUtil.unregister(indexer);
			}

			MessageListener controllerMessageListener =
				baseAlloyControllerImpl.controllerMessageListener;

			if (controllerMessageListener != null) {
				MessageBusUtil.removeDestination(
					baseAlloyControllerImpl.getControllerDestinationName());
			}

			MessageListener schedulerMessageListener =
				baseAlloyControllerImpl.schedulerMessageListener;

			if (schedulerMessageListener != null) {
				try {
					SchedulerEngineHelperUtil.unschedule(
						baseAlloyControllerImpl.getSchedulerJobName(),
						baseAlloyControllerImpl.getMessageListenerGroupName(),
						StorageType.MEMORY_CLUSTERED);

					MessageBusUtil.removeDestination(
						baseAlloyControllerImpl.getSchedulerDestinationName());
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
	}

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		Portlet portlet = liferayPortletConfig.getPortlet();

		FriendlyURLMapper friendlyURLMapper =
			portlet.getFriendlyURLMapperInstance();

		Router router = friendlyURLMapper.getRouter();

		router.urlToParameters(HttpMethods.GET, _defaultRouteParameters);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String path = getPath(actionRequest);

		include(path, actionRequest, actionResponse);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String path = getPath(renderRequest);

		include(path, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String path = getPath(resourceRequest);

		include(path, resourceRequest, resourceResponse);
	}

	protected Map<String, String> getDefaultRouteParameters() {
		/*Map<String, String> defaultRouteParameters =
			new HashMap<String, String[]>();

		defaultRouteParameters.put("controller", new String[] {"assets"});
		defaultRouteParameters.put("action", new String[] {"index"});

		return defaultRouteParameters;*/

		return _defaultRouteParameters;
	}

	protected String getPath(PortletRequest portletRequest) {
		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		Portlet portlet = liferayPortletConfig.getPortlet();

		String controllerPath = ParamUtil.getString(
			portletRequest, "controller");

		if (Validator.isNull(controllerPath)) {
			Map<String, String> defaultRouteParameters =
				getDefaultRouteParameters();

			controllerPath = defaultRouteParameters.get("controller");
		}

		StringBundler sb = new StringBundler(5);

		sb.append("/WEB-INF/jsp/");
		sb.append(portlet.getFriendlyURLMapping());
		sb.append("/controllers/");
		sb.append(controllerPath);
		sb.append("_controller.jsp");

		return sb.toString();
	}

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, PortletException {

		PortletContext portletContext = getPortletContext();

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(portletRequest, portletResponse);
		}
	}

	protected void registerAlloyController(AlloyController alloyController) {
		if (!_alloyControllers.contains(alloyController)) {
			_alloyControllers.add(alloyController);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AlloyPortlet.class);

	private Set<AlloyController> _alloyControllers =
		new HashSet<AlloyController>();
	private Map<String, String> _defaultRouteParameters =
		new HashMap<String, String>();

}