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

package com.liferay.servermanager.util;

import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class FileUploadUtil {

	public static FileItem getFileItem(HttpServletRequest request)
		throws FileUploadException {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		ServletFileUpload servletFileUpload = new ServletFileUpload(
			diskFileItemFactory);

		List<FileItem> fileItems = servletFileUpload.parseRequest(request);

		for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {
				return fileItem;
			}
		}

		return null;
	}

	public static List<FileItem> getFileItems(HttpServletRequest request)
		throws FileUploadException {

		List<FileItem> uploadFileItems = new ArrayList<FileItem>();

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		ServletFileUpload servletFileUpload = new ServletFileUpload(
			diskFileItemFactory);

		List<FileItem> fileItems = servletFileUpload.parseRequest(request);

		for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {
				uploadFileItems.add(fileItem);
			}
		}

		return uploadFileItems;
	}

	public static File getFileItemTempFile(HttpServletRequest request)
		throws Exception {

		FileItem fileItem = getFileItem(request);

		if (fileItem == null) {
			return null;
		}

		File tempDir = new File(
			SystemProperties.get(SystemProperties.TMP_DIR),
			PortalUUIDUtil.generate());

		if (!tempDir.mkdirs()) {
			return null;
		}

		File tempFile = new File(tempDir, fileItem.getName());

		fileItem.write(tempFile);

		return tempFile;
	}

}