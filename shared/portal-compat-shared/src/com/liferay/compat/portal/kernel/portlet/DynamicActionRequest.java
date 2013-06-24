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

package com.liferay.compat.portal.kernel.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;

/**
 * @author Wesley Gong
 */
public class DynamicActionRequest
	extends com.liferay.portal.kernel.portlet.DynamicActionRequest {

	public DynamicActionRequest(ActionRequest actionRequest) {
		super(actionRequest);

		String contentType = actionRequest.getContentType();

		if (Validator.isNotNull(contentType) &&
			contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			_uploadPortletRequest = PortalUtil.getUploadPortletRequest(
				actionRequest);
		}
	}

	public UploadPortletRequest getUploadPortletRequest() {
		return _uploadPortletRequest;
	}

	private UploadPortletRequest _uploadPortletRequest;

}