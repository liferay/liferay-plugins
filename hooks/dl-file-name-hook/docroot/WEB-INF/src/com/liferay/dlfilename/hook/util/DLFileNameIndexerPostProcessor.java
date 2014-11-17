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

package com.liferay.dlfilename.hook.util;

import com.liferay.dlfilename.hook.model.impl.DLFileNameFileEntryImpl;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Preston Crary
 */
public class DLFileNameIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessSummary(
		Summary summary, Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		Field field = document.getField(_DL_FILE_NAME_TITLE_FIELD_NAME);

		if (field != null) {
			summary.setTitle(field.getValue());
		}
	}

	private static final String _DL_FILE_NAME_TITLE_FIELD_NAME =
		ExpandoBridgeIndexerUtil.encodeFieldName(
			DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE);

}