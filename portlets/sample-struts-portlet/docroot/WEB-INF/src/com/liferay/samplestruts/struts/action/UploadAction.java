/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class UploadAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		FileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = upload.parseRequest(request);

		Iterator<FileItem> itr = items.iterator();

		String itemName = StringPool.BLANK;

		while (itr.hasNext()) {
			FileItem item = itr.next();

			if (!item.isFormField()) {
				if (_log.isInfoEnabled()) {
					_log.info("Field name " + item.getFieldName());
				}

				itemName = item.getName();

				if (_log.isInfoEnabled()) {
					_log.info("Name " + itemName);
					_log.info("Content type " + item.getContentType());
					_log.info("In memory " + item.isInMemory());
					_log.info("Size " + item.getSize());
				}
			}
		}

		request.setAttribute("file_name", itemName);

		return mapping.findForward("/sample_struts_portlet/upload_success");
	}

	private static Log _log = LogFactoryUtil.getLog(UploadAction.class);

}