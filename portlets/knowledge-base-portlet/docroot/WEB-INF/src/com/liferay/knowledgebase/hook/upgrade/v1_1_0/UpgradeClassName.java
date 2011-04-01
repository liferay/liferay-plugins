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
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeClassName extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		long classNameId = getClassNameId(_ARTICLE_CLASS_NAME);

		if (classNameId == 0) {
			return;
		}

		updateClassName(_KB_ARTICLE_CLASS_NAME, _ARTICLE_CLASS_NAME);
		updateClassName(_KB_COMMENT_CLASS_NAME, _COMMENT_CLASS_NAME);
		updateClassName(_KB_TEMPLATE_CLASS_NAME, _TEMPLATE_CLASS_NAME);

		updateClassNameCache();
	}

	protected void updateClassName(String newClassName, String oldClassName)
		throws Exception {

		long classNameId = getClassNameId(oldClassName);

		runSQL("delete from ClassName_ where classNameId = " + classNameId);

		StringBundler sb = new StringBundler(2);

		sb.append("update ClassName_ set classNameId = '");
		sb.append(classNameId);
		sb.append("' where value = '");
		sb.append(newClassName);
		sb.append("'");

		runSQL(sb.toString());
	}

	protected void updateClassNameCache() {
		try {
			ClassNameLocalServiceUtil.checkClassNames();
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}
	}

	protected long getClassNameId(String className) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select classNameId from ClassName_ where value = ?");

			ps.setString(1, className);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("classNameId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String _ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Article";

	private static final String _COMMENT_CLASS_NAME =
		"com.liferay.knowledgebase.model.Comment";

	private static final String _KB_ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBArticle";

	private static final String _KB_COMMENT_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBComment";

	private static final String _KB_TEMPLATE_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBTemplate";

	private static final String _TEMPLATE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Template";

	private static Log _log = LogFactoryUtil.getLog(UpgradeClassName.class);

}