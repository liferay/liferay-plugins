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

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jonathan Lee
 * @author Sherry Yang
 */
public class UpgradeGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBuilder sb = new StringBuilder(10);

			sb.append("select classPK from ExpandoValue inner join ");
			sb.append("ExpandoColumn on ((ExpandoValue.columnId = ");
			sb.append("ExpandoColumn.columnId) and (ExpandoColumn.name = ");
			sb.append("'socialOfficeEnabled')) inner join ExpandoTable on ");
			sb.append("((ExpandoValue.tableId = ExpandoTable.tableId) and ");
			sb.append("(ExpandoTable.name = '");
			sb.append(ExpandoTableConstants.DEFAULT_TABLE_NAME);
			sb.append("')) where (ExpandoValue.classNameId = '");
			sb.append(PortalUtil.getClassNameId(Group.class));
			sb.append("') and (ExpandoValue.data_ = 'true')");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long classPK = rs.getLong("classPK");

				LayoutSetLocalServiceUtil.updateLookAndFeel(
					classPK, "so_WAR_sotheme", "01", StringPool.BLANK);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}