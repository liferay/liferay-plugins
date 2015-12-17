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

package com.liferay.microblogs.hook.upgrade.v1_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Matthew Kong
 */
public class UpgradeMicroblogsEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		removeReceiverUserId();
		renameReceiverMicroblogsEntryId();
	}

	protected void removeReceiverUserId() throws Exception {
		if (!hasColumn("MicroblogsEntry", "receiverUserId")) {
			return;
		}

		runSQL("alter table MicroblogsEntry drop column receiverUserId");

		runSQL("drop index IX_7ABB0AB3 on MicroblogsEntry");
	}

	protected void renameReceiverMicroblogsEntryId() throws Exception {
		if (!hasColumn("MicroblogsEntry", "receiverMicroblogsEntryId")) {
			return;
		}

		runSQL("alter table MicroblogsEntry add parentMicroblogsEntryId LONG");

		runSQL(
			"update MicroblogsEntry set parentMicroblogsEntryId = " +
				"receiverMicroblogsEntryId");

		runSQL(
			"alter table MicroblogsEntry drop column " +
				"receiverMicroblogsEntryId");

		runSQL(
			"create index IX_6BD29B9C on MicroblogsEntry " +
				"(type_, parentMicroblogsEntryId)");

		runSQL("drop index IX_36CA3D37 on MicroblogsEntry");
	}

}