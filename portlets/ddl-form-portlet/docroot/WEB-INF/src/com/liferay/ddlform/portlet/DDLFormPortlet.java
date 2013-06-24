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

package com.liferay.ddlform.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.ddlform.DuplicateSubmissionException;
import com.liferay.ddlform.util.DDLFormUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.util.DDLUtil;
import com.liferay.portlet.dynamicdatamapping.StorageFieldRequiredException;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Marcellus Tavares
 */
public class DDLFormPortlet extends MVCPortlet {

	public void saveData(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long recordSetId = ParamUtil.getLong(
			uploadPortletRequest, "recordSetId");

		validate(recordSetId, uploadPortletRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), uploadPortletRequest);

		DDLUtil.updateRecord(0, recordSetId, false, serviceContext);

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(uploadPortletRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, PrincipalException.class.getName())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateSubmissionException ||
			cause instanceof FileSizeException ||
			cause instanceof PrincipalException ||
			cause instanceof StorageFieldRequiredException) {

			return true;
		}

		return false;
	}

	protected void validate(
			long recordSetId, UploadPortletRequest uploadPortletRequest)
		throws PortalException, SystemException {

		boolean multipleSubmissions = ParamUtil.getBoolean(
			uploadPortletRequest, "multipleSubmissions");

		if (!multipleSubmissions &&
			DDLFormUtil.hasSubmitted(uploadPortletRequest, recordSetId)) {

			throw new DuplicateSubmissionException();
		}
	}

}