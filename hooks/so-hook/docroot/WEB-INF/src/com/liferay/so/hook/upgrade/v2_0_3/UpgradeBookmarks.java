/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.upgrade.v2_0_3;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksFolderActionableDynamicQuery;

/**
 * @author Evan Thibodeau
 */
public class UpgradeBookmarks extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new BookmarksFolderActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property columnIdProperty = PropertyFactoryUtil.forName("name");

				dynamicQuery.add(columnIdProperty.eq("Bookmarks"));
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				BookmarksFolder bookmarksFolder = (BookmarksFolder)object;

				Group group = GroupLocalServiceUtil.fetchGroup(
					bookmarksFolder.getGroupId());

				if (group == null) {
					return;
				}

				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
					group.getGroupId(), group.hasPrivateLayouts());

				String themeId = layoutSet.getThemeId();

				if (!themeId.equals("so_WAR_sotheme")) {
					return;
				}

				BookmarksFolderLocalServiceUtil.updateFolder(
					bookmarksFolder.getUserId(), bookmarksFolder.getFolderId(),
					bookmarksFolder.getParentFolderId(),
					bookmarksFolder.getName(), bookmarksFolder.getDescription(),
					true, new ServiceContext());
			}
		};

		actionableDynamicQuery.performActions();
	}

}