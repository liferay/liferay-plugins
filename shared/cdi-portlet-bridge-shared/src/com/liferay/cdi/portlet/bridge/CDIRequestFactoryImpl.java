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

import javax.portlet.ActionRequest;
import javax.portlet.EventRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neil Griffin
 */
public class CDIRequestFactoryImpl implements CDIRequestFactory {

	@Override
	public CDIActionRequest getCDIActionRequest(ActionRequest actionRequest) {
		HttpServletRequestAdapter httpServletRequestAdapter =
			new HttpServletRequestAdapterImpl(actionRequest);

		return new CDIActionRequestImpl(
			actionRequest, httpServletRequestAdapter);
	}

	@Override
	public CDICrossContextRequest getCDICrossContextRequest(
		HttpServletRequest httpServletRequest) {

		return new CDICrossContextRequestImpl(httpServletRequest);
	}

	@Override
	public CDIEventRequest getCDIEventRequest(EventRequest eventRequest) {
		HttpServletRequestAdapter httpServletRequestAdapter =
			new HttpServletRequestAdapterImpl(eventRequest);

		return new CDIEventRequestImpl(eventRequest, httpServletRequestAdapter);
	}

	@Override
	public CDIRenderRequest getCDIRenderRequest(RenderRequest renderRequest) {
		HttpServletRequestAdapter httpServletRequestAdapter =
			new HttpServletRequestAdapterImpl(renderRequest);

		return new CDIRenderRequestImpl(
			renderRequest, httpServletRequestAdapter);
	}

	@Override
	public CDIResourceRequest getCDIResourceRequest(
		ResourceRequest resourceRequest) {

		HttpServletRequestAdapter httpServletRequestAdapter =
			new HttpServletRequestAdapterImpl(resourceRequest);

		return new CDIResourceRequestImpl(
			resourceRequest, httpServletRequestAdapter);
	}

}