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
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.FileVersionWrapper;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.expando.model.ExpandoBridge;

/**
 * @author Preston Crary
 */
public class DLFileNameFileVersionImpl extends FileVersionWrapper {

	public DLFileNameFileVersionImpl(FileVersion fileVersion) {
		super(fileVersion);
	}

	@Override
	public String getTitle() {
		if (DLFileNameThreadLocal.isEnabled()) {
			return super.getTitle();
		}

		ExpandoBridge expandoBridge = getExpandoBridge();

		String displayTitle = (String)expandoBridge.getAttribute(
			DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE, false);

		if (Validator.isNull(displayTitle)) {
			return super.getTitle();
		}

		return displayTitle;
	}

	@Override
	public FileVersion toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}

		return new DLFileNameFileVersionImpl(super.toEscapedModel());
	}

	@Override
	public FileVersion toUnescapedModel() {
		if (isEscapedModel()) {
			return new DLFileNameFileVersionImpl(super.toUnescapedModel());
		}

		return this;
	}

}