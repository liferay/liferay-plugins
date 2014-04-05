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
public interface CDIResponseFactory {

	public CDIActionResponse getCDIActionResponse(
		ActionResponse actionResponse, Locale locale);

	public CDIEventResponse getCDIEventResponse(
		EventResponse eventResponse, Locale locale);

	public CDIRenderResponse getCDIRenderResponse(
		RenderResponse renderResponse, Locale locale);

	public CDIResourceResponse getCDIResourceResponse(
		ResourceResponse resourceResponse, Locale locale);

}