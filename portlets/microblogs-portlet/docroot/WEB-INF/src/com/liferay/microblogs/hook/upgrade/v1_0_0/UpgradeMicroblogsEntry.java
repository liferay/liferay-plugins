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

package com.liferay.microblogs.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Calvin Keum
 */
public class UpgradeMicroblogsEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateMicroBlogsEntry();
	}

	protected void updateMicroBlogsEntry() throws Exception {
		if (hasColumn("MicroblogsEntry", "creatorClassNameId") ||
			hasColumn("MicroblogsEntry", "creatorClassPK")) {

			return;
		}

		runSQL("alter table MicroblogsEntry add creatorClassNameId LONG");
		runSQL("alter table MicroblogsEntry add creatorClassPK LONG");
		runSQL(
			"create index IX_6AA6B164 on MicroblogsEntry (creatorClassNameId," +
				" type_)");
		runSQL(
			"create index IX_14ACFA9 on MicroblogsEntry (creatorClassNameId, " +
				"creatorClassPK, type_)");

		runSQL(
			"update MicroblogsEntry set creatorClassNameId = " +
				PortalUtil.getClassNameId(User.class) +
					", creatorClassPK = MicroblogsEntry.userId");
	}

}