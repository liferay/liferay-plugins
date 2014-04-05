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

import java.util.Locale;

import javax.portlet.ActionResponse;
import javax.portlet.EventResponse;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;

/**
 * @author Neil Griffin
 */
public class CDIResponseFactoryImpl implements CDIResponseFactory {

	@Override
	public CDIActionResponse getCDIActionResponse(
		ActionResponse actionResponse, Locale locale) {

		HttpServletResponseAdapter httpServletResponseAdapter =
			new HttpServletResponseAdapterImpl(actionResponse, locale);

		return new CDIActionResponseImpl(
			actionResponse, httpServletResponseAdapter);
	}

	@Override
	public CDIEventResponse getCDIEventResponse(
		EventResponse eventResponse, Locale locale) {

		HttpServletResponseAdapter httpServletResponseAdapter =
			new HttpServletResponseAdapterImpl(eventResponse, locale);

		return new CDIEventResponseImpl(
			eventResponse, httpServletResponseAdapter);
	}

	@Override
	public CDIRenderResponse getCDIRenderResponse(
		RenderResponse renderResponse, Locale locale) {

		HttpServletResponseAdapter httpServletResponseAdapter =
			new HttpServletResponseAdapterImpl(renderResponse, locale);

		return new CDIRenderResponseImpl(
			renderResponse, httpServletResponseAdapter);
	}

	@Override
	public CDIResourceResponse getCDIResourceResponse(
		ResourceResponse resourceResponse, Locale locale) {

		HttpServletResponseAdapter httpServletResponseAdapter =
			new HttpServletResponseAdapterImpl(resourceResponse, locale);

		return new CDIResourceResponseImpl(
			resourceResponse, httpServletResponseAdapter);
	}

}