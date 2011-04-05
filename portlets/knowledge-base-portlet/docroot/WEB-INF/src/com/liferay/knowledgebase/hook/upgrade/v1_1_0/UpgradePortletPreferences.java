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

	static Map<String, String> adminDefaultPreferencesMap;
	static Map<String, String> articleDefaultPreferencesMap;
	static Map<String, String> displayDefaultPreferencesMap;
	static Map<String, String> oldAdminPreferenceNamesMap;
	static Map<String, String> oldArticlePreferenceNamesMap;
	static Map<String, String> oldDisplayPreferenceNamesMap;

	static {
		adminDefaultPreferencesMap = new HashMap<String, String>();

		adminDefaultPreferencesMap.put("kbArticlesOrderByCol", "priority");
		adminDefaultPreferencesMap.put("kbArticlesOrderByType", "desc");
		adminDefaultPreferencesMap.put("enableKBArticleDescription", "false");
		adminDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "true");
		adminDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		adminDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		adminDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		adminDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		adminDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "true");
		adminDefaultPreferencesMap.put("enableKBTemplateDescription", "false");
		adminDefaultPreferencesMap.put("enableKBTemplateKBComments", "true");
		adminDefaultPreferencesMap.put("showKBTemplateKBComments", "true");

		articleDefaultPreferencesMap = new HashMap<String, String>();

		articleDefaultPreferencesMap.put("kbArticlesOrderByCol", "priority");
		articleDefaultPreferencesMap.put("kbArticlesOrderByType", "desc");
		articleDefaultPreferencesMap.put("enableKBArticleDescription", "false");
		articleDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "true");
		articleDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		articleDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		articleDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		articleDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		articleDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "true");
		articleDefaultPreferencesMap.put(
			"enableKBTemplateDescription", "false");
		articleDefaultPreferencesMap.put("enableKBTemplateKBComments", "true");
		articleDefaultPreferencesMap.put("showKBTemplateKBComments", "true");
		articleDefaultPreferencesMap.put("rssDelta", "20");
		articleDefaultPreferencesMap.put("rssDisplayStyle", "full-content");
		articleDefaultPreferencesMap.put("rssFormat", "atom10");

		displayDefaultPreferencesMap = new HashMap<String, String>();

		displayDefaultPreferencesMap.put("resourcePrimKey", "0");
		displayDefaultPreferencesMap.put("enableKBArticleDescription", "false");
		displayDefaultPreferencesMap.put(
			"enableKBArticleAssetCategories", "true");
		displayDefaultPreferencesMap.put("enableKBArticleAssetTags", "true");
		displayDefaultPreferencesMap.put("enableKBArticleRatings", "false");
		displayDefaultPreferencesMap.put("enableKBArticleKBComments", "true");
		displayDefaultPreferencesMap.put("showKBArticleKBComments", "true");
		displayDefaultPreferencesMap.put(
			"enableKBArticleViewCountIncrement", "false");
		displayDefaultPreferencesMap.put("rssDelta", "20");
		displayDefaultPreferencesMap.put("rssDisplayStyle", "full-content");
		displayDefaultPreferencesMap.put("rssFormat", "atom10");

		oldAdminPreferenceNamesMap = new HashMap<String, String>();

		oldAdminPreferenceNamesMap.put(
			"articlesOrderByCol", "kbArticlesOrderByCol");
		oldAdminPreferenceNamesMap.put(
			"articlesOrderByType", "kbArticlesOrderByType");
		oldAdminPreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		oldAdminPreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		oldAdminPreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		oldAdminPreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		oldAdminPreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		oldAdminPreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		oldAdminPreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");
		oldAdminPreferenceNamesMap.put(
			"enableTemplateDescription", "enableKBTemplateDescription");
		oldAdminPreferenceNamesMap.put(
			"enableTemplateComments", "enableKBTemplateKBComments");
		oldAdminPreferenceNamesMap.put(
			"showTemplateComments", "showKBTemplateKBComments");

		oldArticlePreferenceNamesMap = new HashMap<String, String>();

		oldArticlePreferenceNamesMap.put(
			"articlesOrderByCol", "kbArticlesOrderByCol");
		oldArticlePreferenceNamesMap.put(
			"articlesOrderByType", "kbArticlesOrderByType");
		oldArticlePreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		oldArticlePreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		oldArticlePreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		oldArticlePreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		oldArticlePreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		oldArticlePreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		oldArticlePreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");
		oldArticlePreferenceNamesMap.put(
			"enableTemplateDescription", "enableKBTemplateDescription");
		oldArticlePreferenceNamesMap.put(
			"enableTemplateComments", "enableKBTemplateKBComments");
		oldArticlePreferenceNamesMap.put(
			"showTemplateComments", "showKBTemplateKBComments");
		oldArticlePreferenceNamesMap.put("rssDelta", "rssDelta");
		oldArticlePreferenceNamesMap.put("rssDisplayStyle", "rssDisplayStyle");
		oldArticlePreferenceNamesMap.put("rssFormat", "rssFormat");

		oldDisplayPreferenceNamesMap = new HashMap<String, String>();

		oldDisplayPreferenceNamesMap.put("resourcePrimKey", "resourcePrimKey");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleDescription", "enableKBArticleDescription");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleAssetCategories", "enableKBArticleAssetCategories");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleAssetTags", "enableKBArticleAssetTags");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleRatings", "enableKBArticleRatings");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleComments", "enableKBArticleKBComments");
		oldDisplayPreferenceNamesMap.put(
			"showArticleComments", "showKBArticleKBComments");
		oldDisplayPreferenceNamesMap.put(
			"enableArticleViewCountIncrement",
			"enableKBArticleViewCountIncrement");
		oldDisplayPreferenceNamesMap.put("rssDelta", "rssDelta");
		oldDisplayPreferenceNamesMap.put("rssDisplayStyle", "rssDisplayStyle");
		oldDisplayPreferenceNamesMap.put("rssFormat", "rssFormat");
	}

	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	protected String getName(String rootPortletId, String oldName) {
		if (rootPortletId.equals("1_WAR_knowledgebaseportlet")) {
			return oldAdminPreferenceNamesMap.get(oldName);
		}
		else if (rootPortletId.equals("2_WAR_knowledgebaseportlet")) {
			return oldDisplayPreferenceNamesMap.get(oldName);
		}
		else if (rootPortletId.equals("3_WAR_knowledgebaseportlet")) {
			return oldArticlePreferenceNamesMap.get(oldName);
		}

		return null;
	}

	protected Map<String, String> getDefaultPreferencesMap(
		String rootPortletId) {

		if (rootPortletId.equals("1_WAR_knowledgebaseportlet")) {
			return adminDefaultPreferencesMap;
		}
		else if (rootPortletId.equals("2_WAR_knowledgebaseportlet")) {
			return displayDefaultPreferencesMap;
		}
		else if (rootPortletId.equals("3_WAR_knowledgebaseportlet")) {
			return articleDefaultPreferencesMap;
		}

		return Collections.emptyMap();
	}

	protected String updatePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.fromXML(
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
		"%3_WAR_knowledgebaseportlet_INSTANCE_%"
	};

}