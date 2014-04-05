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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeResourceAction extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasResourceAction("com.liferay.knowledgebase.model.Article")) {
			updateKBArticleResourceActions();
		}

		if (hasResourceAction("com.liferay.knowledgebase.model.Template")) {
			updateKBTemplateResourceActions();
		}
	}

	protected boolean hasResourceAction(String name) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select count(*) from ResourceAction where name = ?");

			ps.setString(1, name);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateKBArticleResourceActions() throws Exception {
		runSQL(
			"delete from ResourceAction where name = " +
				"'com.liferay.knowledgebase.model.KBArticle'");

		runSQL(
			"update ResourceAction set name = " +
				"'com.liferay.knowledgebase.model.KBArticle' where name = " +
					"'com.liferay.knowledgebase.model.Article'");

		runSQL(
			"update ResourceAction set actionId = 'MOVE_KB_ARTICLE' where " +
				"name = 'com.liferay.knowledgebase.model.KBArticle' and " +
					"actionId = 'MOVE'");
	}

	protected void updateKBTemplateResourceActions() throws Exception {
		runSQL(
			"delete from ResourceAction where name = " +
				"'com.liferay.knowledgebase.model.KBTemplate'");

		runSQL(
			"update ResourceAction set name = " +
				"'com.liferay.knowledgebase.model.KBTemplate' where name = " +
					"'com.liferay.knowledgebase.model.Template'");
	}

}