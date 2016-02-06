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

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;

import java.util.Date;

/**
 * @author Matthew Kong
 */
public class BlogsEntryModelListener extends BaseModelListener<BlogsEntry> {

	@Override
	public void onAfterUpdate(BlogsEntry blogsEntry)
		throws ModelListenerException {

		try {
			SocialActivitySet socialActivitySet =
				SocialActivitySetLocalServiceUtil.getClassActivitySet(
					_BLOGS_ENTRY_CLASS_NAME_ID, blogsEntry.getEntryId(),
					SocialActivityKeyConstants.BLOGS_ADD_ENTRY);

			if (socialActivitySet == null) {
				return;
			}

			Date displayDate = blogsEntry.getDisplayDate();

			if (socialActivitySet.getModifiedDate() == displayDate.getTime()) {
				return;
			}

			socialActivitySet.setModifiedDate(displayDate.getTime());

			SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
				socialActivitySet);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	private static final long _BLOGS_ENTRY_CLASS_NAME_ID =
		PortalUtil.getClassNameId(BlogsEntry.class);

}