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

package com.liferay.sampledrools.action;

import com.liferay.compat.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.bi.rules.RulesEngineException;
import com.liferay.portal.kernel.bi.rules.RulesEngineUtil;
import com.liferay.portal.kernel.bi.rules.RulesLanguage;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		updatePreferences(actionRequest, preferences);

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();

			SessionMessages.add(
				actionRequest,
				PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}
	}

	protected void updatePreferences(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String domainName = ParamUtil.getString(actionRequest, "domainName");
		String rules = ParamUtil.getString(actionRequest, "rules");
		String userCustomAttributeNames = ParamUtil.getString(
			actionRequest, "userCustomAttributeNames");
		long[] classNameIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "classNameIds"), 0L);

		if (Validator.isNull(domainName)) {
			SessionErrors.add(actionRequest, "domainName");
		}
		else if (Validator.isNull(rules)) {
			SessionErrors.add(actionRequest, "rules");
		}
		else if (classNameIds.length == 0) {
			SessionErrors.add(actionRequest, "classNameIds");
		}
		else {
			RulesResourceRetriever rulesResourceRetriever =
				new RulesResourceRetriever(
					new StringResourceRetriever(rules),
					String.valueOf(RulesLanguage.DROOLS_RULE_LANGUAGE));

			try {
				RulesEngineUtil.update(
					domainName, rulesResourceRetriever,
					PortalClassLoaderUtil.getClassLoader());
			}
			catch (RulesEngineException ree) {
				_log.error(ree, ree);

				SessionErrors.add(actionRequest, "rulesEngineException");
			}
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.setValue("rules", rules);
			preferences.setValue("domain-name", domainName);
			preferences.setValue(
				"user-custom-attribute-names", userCustomAttributeNames);
			preferences.setValues(
				"class-name-ids", ArrayUtil.toStringArray(classNameIds));
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConfigurationActionImpl.class);

}