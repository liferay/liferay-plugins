/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.WebDirDetector;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.util.Portal;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	protected void doUpgrade() throws Exception {
		init();

		super.doUpgrade();
	}

	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	protected void init() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append(WebDirDetector.getRootDir(getClass().getClassLoader()));
		sb.append("WEB-INF/");
		sb.append(Portal.PORTLET_XML_FILE_NAME_STANDARD);

		Document document = SAXReaderUtil.read(new File(sb.toString()));

		Element rootElement = document.getRootElement();

		Map<String, PortletPreferences> portletNameToPortletPreferencesMap =
			new HashMap<String, PortletPreferences>();

		for (Element portletElement : rootElement.elements("portlet")) {
			String portletName = portletElement.elementText("portlet-name");

			Element portletPreferencesElement = portletElement.element(
				"portlet-preferences");

			PortletPreferences portletPreferences =
				PortletPreferencesFactoryUtil.fromDefaultXML(
					portletPreferencesElement.asXML());

			portletNameToPortletPreferencesMap.put(
				portletName, portletPreferences);
		}

		_portletNameToPortletPreferencesMap =
			portletNameToPortletPreferencesMap;
	}

	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		int pos = portletId.indexOf(PortletConstants.WAR_SEPARATOR);

		String portletName = portletId.substring(0, pos);

		PortletPreferences defaultPortletPreferences =
			_portletNameToPortletPreferencesMap.get(portletName);

		Map<String, String[]> defaultPortletPreferencesMap =
			defaultPortletPreferences.getMap();

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> portletPreferencesMap =
			portletPreferences.getMap();

		for (String oldName : portletPreferencesMap.keySet()) {
			String newName = TextFormatter.format(oldName, TextFormatter.M);
			String[] oldValues = portletPreferencesMap.get(oldName);

			portletPreferences.reset(oldName);

			if (defaultPortletPreferencesMap.containsKey(newName)) {
				portletPreferences.setValues(newName, oldValues);
			}
		}

		for (String name : defaultPortletPreferencesMap.keySet()) {
			if (portletPreferencesMap.containsKey(name)) {
				continue;
			}

			String[] values = defaultPortletPreferencesMap.get(name);

			portletPreferences.setValues(name, values);
		}

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(11);

			sb.append("Updating portlet preferences for {companyId=");
			sb.append(companyId);
			sb.append(", ownerId=");
			sb.append(ownerId);
			sb.append(", ownerType=");
			sb.append(ownerType);
			sb.append(", plid=");
			sb.append(plid);
			sb.append(", portletId=");
			sb.append(portletId);
			sb.append("}");

			_log.debug(sb.toString());
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private static final String[] _PORTLET_IDS = new String[] {
		"1_WAR_knowledgebaseportlet", "2_WAR_knowledgebaseportlet",
		"%3_WAR_knowledgebaseportlet_INSTANCE_%"
	};

	private static Log _log = LogFactoryUtil.getLog(
		UpgradePortletPreferences.class);

	private Map<String, PortletPreferences> _portletNameToPortletPreferencesMap;

}