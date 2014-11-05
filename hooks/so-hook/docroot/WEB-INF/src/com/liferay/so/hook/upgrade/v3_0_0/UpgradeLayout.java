/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.upgrade.v3_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.LayoutUtil;
import com.liferay.so.util.PortletKeys;
import com.liferay.so.util.SocialOfficeConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBuilder sb = new StringBuilder(8);

			sb.append("select Layout.plid from Layout ");
			sb.append(getJoinSQL());
			sb.append("where (Layout.typeSettings like '%");
			sb.append(PortletKeys.ANNOUNCEMENTS);
			sb.append("%') and ");
			sb.append("(Layout.typeSettings not like '%");
			sb.append(PortletKeys.SO_ANNOUNCEMENTS);
			sb.append("%')");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");

				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				LayoutTypePortlet layoutTypePortlet =
					(LayoutTypePortlet)layout.getLayoutType();

				LayoutTemplate layoutTemplate =
					layoutTypePortlet.getLayoutTemplate();

				UnicodeProperties typeSettingsProperties =
					layout.getTypeSettingsProperties();

				for (String columnName : layoutTemplate.getColumns()) {
					String columnValue = typeSettingsProperties.getProperty(
						columnName);

					columnValue = StringUtil.replace(
						columnValue, PortletKeys.ANNOUNCEMENTS,
						PortletKeys.SO_ANNOUNCEMENTS);

					typeSettingsProperties.setProperty(columnName, columnValue);
				}

				layout.setTypeSettingsProperties(typeSettingsProperties);

				LayoutLocalServiceUtil.updateLayout(layout);

				LayoutUtil.addResources(layout, PortletKeys.SO_ANNOUNCEMENTS);
			}

			sb = new StringBuilder(6);
			sb.append("select Layout.plid from Layout ");
			sb.append(getJoinSQL());
			sb.append("where (Layout.typeSettings not like '%");
			sb.append(PortletKeys.SO_ANNOUNCEMENTS);
			sb.append("%') and ");
			sb.append("(Layout.priority = 0)");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");

				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				long layoutSetPrototypeGroupId = getLayoutSetPrototypeGroupId(
					layout.getCompanyId(),
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);

				if (layout.getGroupId() == layoutSetPrototypeGroupId) {
					return;
				}

				LayoutTypePortlet layoutTypePortlet =
					(LayoutTypePortlet)layout.getLayoutType();

				UnicodeProperties typeSettingsProperties =
					layout.getTypeSettingsProperties();

				String columnValue = typeSettingsProperties.getProperty(
					"column-1");

				if (Validator.isNull(columnValue)) {
					return;
				}

				int columnPos = 0;

				if (StringUtil.contains(
						columnValue,
						PortletKeys.MICROBLOGS_STATUS_UPDATE)) {

					columnPos = 1;
				}

				layoutTypePortlet.addPortletId(
					0, PortletKeys.SO_ANNOUNCEMENTS, "column-1", columnPos,
					false);

				layout = layoutTypePortlet.getLayout();

				LayoutLocalServiceUtil.updateLayout(layout);

				LayoutUtil.addResources(layout, PortletKeys.SO_ANNOUNCEMENTS);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected String getJoinSQL() {
		StringBuilder sb = new StringBuilder(18);

		sb.append("inner join ExpandoValue on ");
		sb.append("((ExpandoValue.classPK = Layout.groupId) and ");
		sb.append("(ExpandoValue.data_ = 'true')) ");

		sb.append("inner join ExpandoColumn on ");
		sb.append("((ExpandoColumn.columnId = ExpandoValue.columnId) and ");
		sb.append("(ExpandoColumn.name = 'socialOfficeEnabled')) ");

		sb.append("inner join ExpandoTable on ");
		sb.append("((ExpandoTable.tableId = ExpandoValue.tableId) and ");
		sb.append("(ExpandoTable.name = '");
		sb.append(ExpandoTableConstants.DEFAULT_TABLE_NAME);
		sb.append("')) ");

		sb.append("inner join Group_ on ");
		sb.append("((Group_.groupId = Layout.groupId) and ");
		sb.append("((Layout.privateLayout = true) or ");
		sb.append("((Layout.privateLayout = false) and ");
		sb.append("(Group_.classNameId != ");
		sb.append(PortalUtil.getClassNameId(User.class));
		sb.append(")))) ");

		return sb.toString();
	}

	protected long getLayoutSetPrototypeGroupId(
			long companyId, String layoutSetPrototypeKey)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
				companyId, layoutSetPrototypeKey);

		if (layoutSetPrototype != null) {
			return layoutSetPrototype.getGroupId();
		}

		return 0;
	}

}