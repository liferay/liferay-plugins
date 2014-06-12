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

package com.liferay.so.activities.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;

import java.util.Date;

/**
 * @author Matthew Kong
 */
public class BlogsEntryListener extends BaseModelListener<BlogsEntry> {

	@Override
	public void onAfterUpdate(BlogsEntry blogsEntry)
		throws ModelListenerException {

		try {
			SocialActivitySet activitySet =
				SocialActivitySetLocalServiceUtil.getClassActivitySet(
					_BLOGS_ENTRY_CLASS_NAME_ID, blogsEntry.getEntryId(),
					SocialActivityKeyConstants.BLOGS_ADD_ENTRY);

			if (activitySet == null) {
				return;
			}

			Date displayDate = blogsEntry.getDisplayDate();

			if (activitySet.getModifiedDate() == displayDate.getTime()) {
				return;
			}

			activitySet.setModifiedDate(displayDate.getTime());

			SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
				activitySet);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	private static final long _BLOGS_ENTRY_CLASS_NAME_ID =
		PortalUtil.getClassNameId(BlogsEntry.class);

}