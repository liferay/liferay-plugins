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

package com.liferay.samplestruts.struts.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * @author Brian Wing Shun Chan
 */
public class UploadAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		MultipartRequestHandler multipartRequestHandler =
			form.getMultipartRequestHandler();

		Hashtable<String, FormFile> fileElements =
			multipartRequestHandler.getFileElements();

		String fileName = StringPool.BLANK;

		for (Map.Entry<String, FormFile> entry : fileElements.entrySet()) {
			if (_log.isInfoEnabled()) {
				_log.info("Field name " + entry.getKey());
			}

			FormFile formFile = entry.getValue();

			fileName = formFile.getFileName();

			if (_log.isInfoEnabled()) {
				_log.info("Name " + fileName);
				_log.info("Content type " + formFile.getContentType());
				_log.info("Size " + formFile.getFileSize());
			}
		}

		request.setAttribute("file_name", fileName);

		return mapping.findForward("/sample_struts_portlet/upload_success");
	}

	private static Log _log = LogFactoryUtil.getLog(UploadAction.class);

}