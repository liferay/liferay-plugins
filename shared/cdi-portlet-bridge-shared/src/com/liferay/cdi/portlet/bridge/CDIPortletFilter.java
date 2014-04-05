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

package com.liferay.cdi.portlet.bridge;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.EventFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;

/**
 * @author Neil Griffin
 */
public class CDIPortletFilter
	implements ActionFilter, EventFilter, RenderFilter, ResourceFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ActionRequest actionRequest, ActionResponse actionResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		CDIRequestFactory cdiRequestFactory = getCDIRequestFactory();

		actionRequest = cdiRequestFactory.getCDIActionRequest(actionRequest);

		CDIResponseFactory cdiResponseFactory = getCDIResponseFactory();

		actionResponse = cdiResponseFactory.getCDIActionResponse(
			actionResponse, actionRequest.getLocale());

		filterChain.doFilter(actionRequest, actionResponse);
	}

	@Override
	public void doFilter(
			EventRequest eventRequest, EventResponse eventResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		CDIRequestFactory cdiRequestFactory = getCDIRequestFactory();

		eventRequest = cdiRequestFactory.getCDIEventRequest(eventRequest);

		CDIResponseFactory cdiResponseFactory = getCDIResponseFactory();

		eventResponse = cdiResponseFactory.getCDIEventResponse(
			eventResponse, eventRequest.getLocale());

		filterChain.doFilter(eventRequest, eventResponse);
	}

	@Override
	public void doFilter(
			RenderRequest renderRequest, RenderResponse renderResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		CDIRequestFactory cdiRequestFactory = getCDIRequestFactory();

		renderRequest = cdiRequestFactory.getCDIRenderRequest(renderRequest);

		CDIResponseFactory cdiResponseFactory = getCDIResponseFactory();

		renderResponse = cdiResponseFactory.getCDIRenderResponse(
			renderResponse, renderRequest.getLocale());

		filterChain.doFilter(renderRequest, renderResponse);
	}

	@Override
	public void doFilter(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		CDIRequestFactory cdiRequestFactory = getCDIRequestFactory();

		resourceRequest = cdiRequestFactory.getCDIResourceRequest(
			resourceRequest);

		CDIResponseFactory cdiResponseFactory = getCDIResponseFactory();

		resourceResponse = cdiResponseFactory.getCDIResourceResponse(
			resourceResponse, resourceRequest.getLocale());

		filterChain.doFilter(resourceRequest, resourceResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	protected CDIRequestFactory getCDIRequestFactory() {
		if (_cdiRequestFactory == null) {
			_cdiRequestFactory =
				(CDIRequestFactory)CDIBeanManagerUtil.getManagedBeanReference(
					CDIRequestFactory.class);
		}

		return _cdiRequestFactory;
	}

	protected CDIResponseFactory getCDIResponseFactory() {
		if (_cdiResponseFactory == null) {
			_cdiResponseFactory =
				(CDIResponseFactory)CDIBeanManagerUtil.getManagedBeanReference(
					CDIResponseFactory.class);
		}

		return _cdiResponseFactory;
	}

	private CDIRequestFactory _cdiRequestFactory;
	private CDIResponseFactory _cdiResponseFactory;

}