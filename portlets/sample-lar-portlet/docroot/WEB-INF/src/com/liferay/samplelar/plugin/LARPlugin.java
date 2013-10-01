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

package com.liferay.samplelar.plugin;

import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerChoice;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipWriter;

import java.util.Date;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Raymond Augé
 */
public class LARPlugin extends BasePortletDataHandler {

	public LARPlugin() {
		setExportControls(
			new PortletDataHandlerBoolean(
				_NAMESPACE, "export-sample-lar-portlet-data", true,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(
						_NAMESPACE, "create-readme", true, false),
					new PortletDataHandlerChoice(
						_NAMESPACE, "data-type", 1, new String[] {"csv", "xml"})
				}));
		setImportControls(
			new PortletDataHandlerBoolean(
				_NAMESPACE, "import-sample-lar-portlet-data", true, true));
		setPublishToLiveByDefault(_PUBLISH_TO_LIVE_BY_DEFAULT);
	}

	@Override
	public PortletPreferences deleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences preferences)
		throws PortletDataException {

		return null;
	}

	@Override
	public String exportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences preferences)
		throws PortletDataException {

		Map parameterMap = portletDataContext.getParameterMap();

		boolean exportData = portletDataContext.getBooleanParameter(
			_NAMESPACE, "export-sample-lar-portlet-data");

		if (_log.isDebugEnabled()) {
			if (exportData) {
				_log.debug("Exporting data is enabled");
			}
			else {
				_log.debug("Exporting data is disabled");
			}
		}

		if (!exportData) {
			return null;
		}

		try {
			long exportDate = System.currentTimeMillis();

			if (_log.isInfoEnabled()) {
				_log.info("Exporting LAR on " + new Date(exportDate));
			}

			preferences.setValue(
				"last-export-date", String.valueOf(exportDate));

			preferences.store();

			String data = "<data-file />";

			ZipWriter zipWriter = portletDataContext.getZipWriter();

			if (zipWriter != null) {
				boolean createReadMe = portletDataContext.getBooleanParameter(
					_NAMESPACE, "create-readme");

				if (createReadMe) {
					if (_log.isInfoEnabled()) {
						_log.info("Writing to zip");
					}

					zipWriter.addEntry(
						portletId + "/README.txt", "Test writing to zip.");
				}

				String dataType = MapUtil.getString(
					parameterMap, "data-type", "xml");

				if (Validator.equals(dataType, "csv")) {
					StringBuilder csv = new StringBuilder();

					csv.append("data 1," + new Date() + "\n");
					csv.append("data 2," + new Date() + "\n");

					String filePath = portletId + "/data.csv";

					data = "<data-file>" + filePath + "</data-file>";

					zipWriter.addEntry(filePath, csv.toString());
				}
				else if (Validator.equals(dataType, "xml")) {
					StringBuilder xml = new StringBuilder();

					xml.append("<?xml version=\"1.0\"?>\n\n");
					xml.append("<records>\n");
					xml.append("\t<record>\n");
					xml.append("\t\t<field>data 1</field>\n");
					xml.append("\t\t<field>" + new Date() + "</field>\n");
					xml.append("\t</record>\n");
					xml.append("\t<record>\n");
					xml.append("\t\t<field>data 2</field>\n");
					xml.append("\t\t<field>" + new Date() + "</field>\n");
					xml.append("\t</record>\n");
					xml.append("</records>");

					String filePath = portletId + "/data.xml";

					data = "<data-file>" + filePath + "</data-file>";

					zipWriter.addEntry(filePath, xml.toString());
				}
			}

			return data;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	@Override
	public PortletPreferences importData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences preferences, String data)
		throws PortletDataException {

		boolean importData = portletDataContext.getBooleanParameter(
			_NAMESPACE, "import-sample-lar-portlet-data");

		if (_log.isDebugEnabled()) {
			if (importData) {
				_log.debug("Importing data is enabled");
			}
			else {
				_log.debug("Importing data is disabled");
			}
		}

		if (!importData) {
			return null;
		}

		try {
			long importDate = System.currentTimeMillis();

			preferences.setValue(
				"last-import-date", String.valueOf(importDate));

			if (_log.isInfoEnabled()) {
				_log.info("Importing data " + data);
			}

			ZipReader zipReader = portletDataContext.getZipReader();

			if (zipReader != null) {
				_log.info(
					"From README file:\n\n" +
						zipReader.getEntryAsString(portletId + "/README.txt"));
			}

			return preferences;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	private static final String _NAMESPACE = "lar-plugin";

	private static final boolean _PUBLISH_TO_LIVE_BY_DEFAULT = true;

	private static Log _log = LogFactoryUtil.getLog(LARPlugin.class);

}