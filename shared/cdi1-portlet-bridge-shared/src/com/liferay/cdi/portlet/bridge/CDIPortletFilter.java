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

package com.liferay.cdi.portlet.bridge;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * @author Neil Griffin
 */
public class CDIPortletFilter
	implements ActionFilter, EventFilter, RenderFilter, ResourceFilter {

	@Override
	public boolean accept(XMLEvent xmlEvent) {

		return false;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
		ActionRequest actionRequest, ActionResponse actionResponse,
		FilterChain filterChain) throws IOException, PortletException {

		CDIActionRequest cdiActionRequest =
			getCDIRequestFactory().getCDIActionRequest(actionRequest);

		CDIActionResponse cdiActionResponse =
			getCDIResponseFactory().getCDIActionResponse(
				actionResponse, actionRequest.getLocale());

		filterChain.doFilter(cdiActionRequest, cdiActionResponse);
	}

	@Override
	public void doFilter(
		RenderRequest renderRequest, RenderResponse renderResponse,
		FilterChain filterChain) throws IOException, PortletException {

		CDIRenderRequest cdiRenderRequest =
			getCDIRequestFactory().getCDIRenderRequest(renderRequest);

		Locale locale = renderRequest.getLocale();

		CDIRenderResponse cdiRenderResponse =
			getCDIResponseFactory().getCDIRenderResponse(renderResponse, locale);

		filterChain.doFilter(cdiRenderRequest, cdiRenderResponse);
	}

	@Override
	public void doFilter(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse,
		FilterChain filterChain) throws IOException, PortletException {

		CDIResourceRequest cdiResourceRequest =
			getCDIRequestFactory().getCDIResourceRequest(resourceRequest);

		Locale locale = resourceRequest.getLocale();

		CDIResourceResponse cdiResourceResponse =
			getCDIResponseFactory().getCDIResourceResponse(
				resourceResponse, locale);

		filterChain.doFilter(cdiResourceRequest, cdiResourceResponse);
	}

	protected CDIRequestFactory getCDIRequestFactory() {
		
		if (_cdiRequestFactory == null) {
			_cdiRequestFactory = (CDIRequestFactory)
				CDIUtil.getManagedBeanReference(CDIRequestFactory.class);
		}
		return _cdiRequestFactory;
	}

	protected CDIResponseFactory getCDIResponseFactory() {

		if (_cdiResponseFactory == null) {
			_cdiResponseFactory = (CDIResponseFactory)
				CDIUtil.getManagedBeanReference(CDIResponseFactory.class);
		}
		return _cdiResponseFactory;
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		
		// Unable to initialize _cdiRequestFactory and _cdiResponseFactory
		// here because there is no guarantee that the CDIContextListener
		// has been initialized at the time this method is called.
	}

	private CDIRequestFactory _cdiRequestFactory;
	private CDIResponseFactory _cdiResponseFactory;

}