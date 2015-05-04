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

package com.liferay.knowledgebase.hook.upgrade.v1_3_4;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Adolfo Pérez
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		for (int i = 0; i < _PREFERENCE_NAMES.length; i++) {
			String sourcePreferenceName = _PREFERENCE_NAMES[i][0];
			String targetPreferenceName = _PREFERENCE_NAMES[i][1];

			String value = portletPreferences.getValue(
				sourcePreferenceName, null);

			portletPreferences.setValue(targetPreferenceName, value);
			portletPreferences.reset(sourcePreferenceName);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private static final String[] _PORTLET_IDS = {"1_WAR_knowledgebaseportlet"};

	private static final String[][] _PREFERENCE_NAMES = {
		{
			"emailKBArticleFeedbackInProgressEnabled",
			"emailKBArticleSuggestionInProgressEnabled"
		},
		{
			"emailKBArticleFeedbackInProgressSubject",
			"emailKBArticleSuggestionInProgressSubject"
		},
		{
			"emailKBArticleFeedbackInProgressBody",
			"emailKBArticleSuggestionInProgressBody"
		},
		{
			"emailKBArticleFeedbackReceivedEnabled",
			"emailKBArticleSuggestionReceivedEnabled"
		},
		{
			"emailKBArticleFeedbackReceivedSubject",
			"emailKBArticleSuggestionReceivedSubject"
		},
		{
			"emailKBArticleFeedbackReceivedBody",
			"emailKBArticleSuggestionReceivedBody"
		},
		{
			"emailKBArticleFeedbackResolvedEnabled",
			"emailKBArticleSuggestionResolvedEnabled"
		},
		{
			"emailKBArticleFeedbackResolvedSubject",
			"emailKBArticleSuggestionResolvedSubject"
		},
		{
			"emailKBArticleFeedbackResolvedBody",
			"emailKBArticleSuggestionResolvedBody"
		}
	};

}