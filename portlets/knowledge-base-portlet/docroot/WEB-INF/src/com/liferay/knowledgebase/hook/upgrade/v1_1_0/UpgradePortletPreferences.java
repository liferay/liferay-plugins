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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.CamelCaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 */
public class UpgradePortletPreferences
	extends CamelCaseUpgradePortletPreferences {

	protected Map<String, String> getDefaultPreferencesMap(
		String rootPortletId) {

		if (rootPortletId.equals("1_WAR_knowledgebaseportlet")) {
			return _adminDefaultPreferencesMap;
		}
		else if (rootPortletId.equals("2_WAR_knowledgebaseportlet")) {
			return _displayDefaultPreferencesMap;
		}
		else if (rootPortletId.equals("3_WAR_knowledgebaseportlet")) {
			return _articleDefaultPreferencesMap;
		}

		return Collections.emptyMap();
	}

	protected String getName(String rootPortletId, String oldName) {
		if (rootPortletId.equals("1_WAR_knowledgebaseportlet")) {
			return _oldAdminPreferenceNamesMap.get(oldName);
		}
		else if (rootPortletId.equals("2_WAR_knowledgebaseportlet")) {
			return _oldDisplayPreferenceNamesMap.get(oldName);
		}
		else if (rootPortletId.equals("3_WAR_knowledgebaseportlet")) {
			return _oldArticlePreferenceNamesMap.get(oldName);
		}

		return null;
	}

	@Override
	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	protected String updatePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences preferences = PortletPreferencesFactoryUtil.fromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> preferencesMap = preferences.getMap();

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		for (String oldName : preferencesMap.keySet()) {
			String newName = getName(rootPortletId, oldName);
			String[] oldValues = preferencesMap.get(oldName);

			preferences.reset(oldName);

			if (newName != null) {
				preferences.setValues(newName, oldValues);
			}
		}

		Map<String, String> defaultPreferencesMap = getDefaultPreferencesMap(
			rootPortletId);

		for (String name : defaultPreferencesMap.keySet()) {
			if (preferences.getValues(name, null) == null) {
				preferences.setValues(
					name, StringUtil.split(defaultPreferencesMap.get(name)));
			}
		}

		return PortletPreferencesFactoryUtil.toXML(preferences);
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		String preferences = super.upgradePreferences(
			companyId, ownerId, ownerType, plid, portletId, xml);

		return updatePreferences(
			companyId, ownerId, ownerType, plid, portletId, preferences);
	}

	private static final String[] _PORTLET_IDS = new String[] {
		"1_WAR_knowledgebaseportlet", "2_WAR_knowledgebaseportlet",
		"3_WAR_knowledgebaseportlet_INSTANCE_%"
	};

	private static Map<String, String> _adminDefaultPreferencesMap;
	private static Map<String, String> _articleDefaultPreferencesMap;
	private static Map<String, String> _displayDefaultPreferencesMap;
	private static Map<String, String> _oldAdminPreferenceNamesMap;
	private static Map<String, String> _oldArticlePreferenceNamesMap;
	private static Map<String, String> _oldDisplayPreferenceNamesMap;

	static {
		_adminDefaultPreferencesMap = new HashMap<String, String>();

		_adminDefaultPreferencesMap.put("kbArticlesOrderByCol", "priority");
		_adminDefaultPreferencesMap.put("kbArticlesOrderByType", "desc");
		_adminDefaultPreferencesMap.put("enableKBArticleDescription", "false");
		_adminDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "false");
		_adminDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		_adminDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		_adminDefaultPreferencesMap.put("showKBArticleAssetEntries", "true");
		_adminDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		_adminDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		_adminDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "true");
		_adminDefaultPreferencesMap.put("enableKBStructureKBComments", "true");
		_adminDefaultPreferencesMap.put("showKBStructureKBComments", "true");
		_adminDefaultPreferencesMap.put("enableKBTemplateKBComments", "true");
		_adminDefaultPreferencesMap.put("showKBTemplateKBComments", "true");

		_articleDefaultPreferencesMap = new HashMap<String, String>();

		_articleDefaultPreferencesMap.put("resourcePrimKey", "0");
		_articleDefaultPreferencesMap.put(
			"enableKBArticleDescription", "false");
		_articleDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "false");
		_articleDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		_articleDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		_articleDefaultPreferencesMap.put("showKBArticleAssetEntries", "true");
		_articleDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		_articleDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		_articleDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "true");
		_articleDefaultPreferencesMap.put("rssDelta", "20");
		_articleDefaultPreferencesMap.put("rssDisplayStyle", "full-content");
		_articleDefaultPreferencesMap.put("rssFormat", "atom10");

		_displayDefaultPreferencesMap = new HashMap<String, String>();

		_displayDefaultPreferencesMap.put("kbArticlesOrderByCol", "priority");
		_displayDefaultPreferencesMap.put("kbArticlesOrderByType", "desc");
		_displayDefaultPreferencesMap.put(
			"showKBArticlePriorityColumn", "true");
		_displayDefaultPreferencesMap.put("showKBArticleAuthorColumn", "true");
		_displayDefaultPreferencesMap.put(
			"showKBArticleCreateDateColumn", "true");
		_displayDefaultPreferencesMap.put(
			"showKBArticleModifiedDateColumn", "true");
		_displayDefaultPreferencesMap.put("showKBArticleStatusColumn", "true");
		_displayDefaultPreferencesMap.put("showKBArticleViewsColumn", "true");
		_displayDefaultPreferencesMap.put(
			"enableKBArticleDescription", "false");
		_displayDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "false");
		_displayDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		_displayDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		_displayDefaultPreferencesMap.put("showKBArticleAssetEntries", "true");
		_displayDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		_displayDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		_displayDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "true");
		_displayDefaultPreferencesMap.put("enableKBTemplateKBComments", "true");
		_displayDefaultPreferencesMap.put("showKBTemplateKBComments", "true");
		_displayDefaultPreferencesMap.put("rssDelta", "20");
		_displayDefaultPreferencesMap.put("rssDisplayStyle", "full-content");
		_displayDefaultPreferencesMap.put("rssFormat", "atom10");

		_oldAdminPreferenceNamesMap = new HashMap<String, String>();

		_oldAdminPreferenceNamesMap.put(
			"articlesOrderByCol", "kbArticlesOrderByCol");
		_oldAdminPreferenceNamesMap.put(
			"articlesOrderByType", "kbArticlesOrderByType");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		_oldAdminPreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		_oldAdminPreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");
		_oldAdminPreferenceNamesMap.put(
			"enableTemplateComments", "enableKBTemplateKBComments");
		_oldAdminPreferenceNamesMap.put(
			"showTemplateComments", "showKBTemplateKBComments");

		_oldArticlePreferenceNamesMap = new HashMap<String, String>();

		_oldArticlePreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		_oldArticlePreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		_oldArticlePreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		_oldArticlePreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		_oldArticlePreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		_oldArticlePreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		_oldArticlePreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");

		_oldDisplayPreferenceNamesMap = new HashMap<String, String>();

		_oldDisplayPreferenceNamesMap.put(
			"articlesOrderByCol", "kbArticlesOrderByCol");
		_oldDisplayPreferenceNamesMap.put(
			"articlesOrderByType", "kbArticlesOrderByType");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		_oldDisplayPreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		_oldDisplayPreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");
		_oldDisplayPreferenceNamesMap.put(
			"enableTemplateComments", "enableKBTemplateKBComments");
		_oldDisplayPreferenceNamesMap.put(
			"showTemplateComments", "showKBTemplateKBComments");
	}

}