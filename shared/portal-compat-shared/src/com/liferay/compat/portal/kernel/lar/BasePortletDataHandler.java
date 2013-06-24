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

package com.liferay.compat.portal.kernel.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BasePortletDataHandler
	extends com.liferay.portal.kernel.lar.BasePortletDataHandler {

	public String[] getDataPortletPreferences() {
		return _dataPortletPreferences;
	}

	public PortletDataHandlerControl[] getExportControls() {
		return _exportControls;
	}

	public PortletDataHandlerControl[] getExportMetadataControls() {
		return _exportMetadataControls;
	}

	public PortletDataHandlerControl[] getImportControls() {
		return _importControls;
	}

	public PortletDataHandlerControl[] getImportMetadataControls() {
		return _importMetadataControls;
	}

	public boolean isAlwaysExportable() {
		return _alwaysExportable;
	}

	public boolean isAlwaysStaged() {
		return _alwaysStaged;
	}

	public boolean isDataLocalized() {
		return _dataLocalized;
	}

	public boolean isPublishToLiveByDefault() {
		return _publishToLiveByDefault;
	}

	protected Element addExportDataRootElement(
		PortletDataContext portletDataContext) {

		Document document = SAXReaderUtil.createDocument();

		Class<?> clazz = getClass();

		return document.addElement(clazz.getSimpleName());
	}

	protected String getExportDataRootElementString(Element rootElement) {
		if (rootElement == null) {
			return StringPool.BLANK;
		}

		try {
			Document document = rootElement.getDocument();

			return document.formattedString();
		}
		catch (IOException ioe) {
			return StringPool.BLANK;
		}
	}

	protected void setAlwaysExportable(boolean alwaysExportable) {
		_alwaysExportable = alwaysExportable;
	}

	protected void setAlwaysStaged(boolean alwaysStaged) {
		_alwaysStaged = alwaysStaged;
	}

	protected void setDataLocalized(boolean dataLocalized) {
		_dataLocalized = dataLocalized;
	}

	protected void setDataPortletPreferences(String... dataPortletPreferences) {
		_dataPortletPreferences = dataPortletPreferences;
	}

	protected void setExportControls(
		PortletDataHandlerControl... exportControls) {

		_exportControls = exportControls;

		setImportControls(exportControls);
	}

	protected void setExportMetadataControls(
		PortletDataHandlerControl... exportMetadataControls) {

		_exportMetadataControls = exportMetadataControls;

		setImportMetadataControls(exportMetadataControls);
	}

	protected void setImportControls(
		PortletDataHandlerControl... importControls) {

		_importControls = importControls;
	}

	protected void setImportMetadataControls(
		PortletDataHandlerControl... importMetadataControls) {

		_importMetadataControls = importMetadataControls;
	}

	protected void setPublishToLiveByDefault(boolean publishToLiveByDefault) {
		_publishToLiveByDefault = publishToLiveByDefault;
	}

	private boolean _alwaysExportable;
	private boolean _alwaysStaged;
	private boolean _dataLocalized;
	private String[] _dataPortletPreferences = new String[0];
	private PortletDataHandlerControl[] _exportControls =
		new PortletDataHandlerControl[0];
	private PortletDataHandlerControl[] _exportMetadataControls =
		new PortletDataHandlerControl[0];
	private PortletDataHandlerControl[] _importControls =
		new PortletDataHandlerControl[0];
	private PortletDataHandlerControl[] _importMetadataControls =
		new PortletDataHandlerControl[0];
	private boolean _publishToLiveByDefault;

}