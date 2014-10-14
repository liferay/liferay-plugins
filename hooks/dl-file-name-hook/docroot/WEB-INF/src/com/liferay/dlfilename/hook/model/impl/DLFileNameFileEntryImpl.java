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

package com.liferay.dlfilename.hook.model.impl;

import com.liferay.dlfilename.hook.util.DLFileNameThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileEntryWrapper;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.expando.model.ExpandoBridge;

/**
 * @author Preston Crary
 */
public class DLFileNameFileEntryImpl extends FileEntryWrapper {

	public static final String EXPAND_COLUMN_NAME_DISPLAY_TITLE =
		"dlFileNameFileEntryDisplayTitle";

	public DLFileNameFileEntryImpl(FileEntry fileEntry) {
		super(fileEntry);
	}

	@Override
	public FileVersion getFileVersion()
		throws PortalException, SystemException {

		FileVersion fileVersion = super.getFileVersion();

		return new DLFileNameFileVersionImpl(fileVersion);
	}

	@Override
	public FileVersion getLatestFileVersion()
		throws PortalException, SystemException {

		FileVersion fileVersion = super.getLatestFileVersion();

		return new DLFileNameFileVersionImpl(fileVersion);
	}

	@Override
	public String getTitle() {
		if (DLFileNameThreadLocal.isEnabled()) {
			return super.getTitle();
		}

		ExpandoBridge expandoBridge = getExpandoBridge();

		String displayTitle = (String)expandoBridge.getAttribute(
			EXPAND_COLUMN_NAME_DISPLAY_TITLE, false);

		if (Validator.isNull(displayTitle)) {
			return super.getTitle();
		}

		return displayTitle;
	}

	@Override
	public FileEntry toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}

		return new DLFileNameFileEntryImpl(super.toEscapedModel());
	}

	@Override
	public FileEntry toUnescapedModel() {
		if (isEscapedModel()) {
			return new DLFileNameFileEntryImpl(super.toUnescapedModel());
		}

		return this;
	}

}