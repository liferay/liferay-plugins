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

package com.liferay.calendar.hook.upgrade.v1_0_0;

import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.persistence.LayoutActionableDynamicQuery;

import java.io.IOException;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Adam Brandizzi
 */
public class UpgradeLayouts extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new LayoutActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) throws SystemException {
				UnicodeProperties layoutTypeSettings = new UnicodeProperties(
					true);

				Layout layout = (Layout)object;

				try {
					layoutTypeSettings.load(layout.getTypeSettings());
				}
				catch (IOException e) {
					throw new SystemException(e);
				}

				boolean propertiesChanged = false;

				for (String key : layoutTypeSettings.keySet()) {
					String[] property = StringUtil.split(key, StringPool.DASH);

					if ((property.length == 2) &&
						Validator.equals(property[0], "column") &&
						Validator.isNumber(property[1])) {

						String value = layoutTypeSettings.get(key);

						String[] values = StringUtil.split(
							value, StringPool.COMMA);

						for (int i = 0; i < values.length; i++) {
							if (values[i].equals("8")) {
								values[i] = PortletKeys.CALENDAR;
								propertiesChanged = true;
							}
						}

						layoutTypeSettings.setProperty(
							key, StringUtil.merge(values, StringPool.COMMA));
					}
				}

				if (propertiesChanged) {
					layout.setTypeSettings(layoutTypeSettings.toString());
					LayoutLocalServiceUtil.updateLayout(layout);
				}
			}

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property typeSettingsProperty = PropertyFactoryUtil.forName(
					"typeSettings");

				Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

				disjunction.add(typeSettingsProperty.like("%column-%=8\n%"));
				disjunction.add(typeSettingsProperty.like("%column-%=8,%"));
				disjunction.add(typeSettingsProperty.like("%column-%=8"));
				disjunction.add(typeSettingsProperty.like("%column-%=%,8\n%"));
				disjunction.add(typeSettingsProperty.like("%column-%=%,8,%"));
				disjunction.add(typeSettingsProperty.like("%column-%=%,8"));

				dynamicQuery.add(disjunction);
			}
		};

		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		actionableDynamicQuery.performActions();

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(6);

			sb.append("Calendar layouts update ");
			sb.append(stopWatch.getTime());
			sb.append(" ms.");

			_log.info(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeLayouts.class);

}