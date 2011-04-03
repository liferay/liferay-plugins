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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeResourceAction extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		updateResourceActions();
	}

	protected void updateResourceActions() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("select * from ResourceAction where name in ('");
			sb.append(_ARTICLE_CLASS_NAME);
			sb.append("', '");
			sb.append(_TEMPLATE_CLASS_NAME);
			sb.append("')");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourceActionId = rs.getLong("resourceActionId");
				String name = rs.getString("name");
				String actionId = rs.getString("actionId");
				long bitwiseValue = rs.getLong("bitwiseValue");

				String newName = null;

				if (name.equals(_ARTICLE_CLASS_NAME)) {
					newName = _KB_ARTICLE_CLASS_NAME;
				}
				else {
					newName = _KB_TEMPLATE_CLASS_NAME;
				}

				sb = new StringBundler(7);

				sb.append("delete from ResourceAction where name = '");
				sb.append(newName);
				sb.append("' and actionId = '");
				sb.append(actionId);
				sb.append("' and bitwiseValue = '");
				sb.append(bitwiseValue);
				sb.append("'");

				if (_log.isDebugEnabled()) {
					_log.debug(sb.toString());
				}

				runSQL(sb.toString());

				sb = new StringBundler(5);

				sb.append("update ResourceAction set name = '");
				sb.append(newName);
				sb.append("' where resourceActionId = '");
				sb.append(resourceActionId);
				sb.append("'");

				if (_log.isDebugEnabled()) {
					_log.debug(sb.toString());
				}

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String _ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Article";

	private static final String _KB_ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBArticle";

	private static final String _KB_TEMPLATE_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBTemplate";

	private static final String _TEMPLATE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Template";

	private static Log _log = LogFactoryUtil.getLog(
		UpgradeResourceAction.class);

}