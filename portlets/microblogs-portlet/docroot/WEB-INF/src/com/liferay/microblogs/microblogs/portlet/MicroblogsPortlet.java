/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.microblogs.portlet;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jonathan Lee
 */
public class MicroblogsPortlet extends MVCPortlet {

	public void deleteMicroblogsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		MicroblogsEntryServiceUtil.deleteMicroblogsEntry(microblogsEntryId);
	}

	public void updateMicroblogsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		String content = ParamUtil.getString(actionRequest, "content");
		int type = ParamUtil.getInteger(actionRequest, "type");
		long receiverUserId = ParamUtil.getLong(
			actionRequest, "receiverUserId");
		long receiverMicroblogsEntryId = ParamUtil.getLong(
			actionRequest, "receiverMicroblogsEntryId");
		int socialRelationType = ParamUtil.getInteger(
			actionRequest, "socialRelationType");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MicroblogsEntry.class.getName(), actionRequest);

		String[] assetTagNames = getAssetTagNames(content);

		serviceContext.setAssetTagNames(assetTagNames);

		if (microblogsEntryId > 0) {
			MicroblogsEntryServiceUtil.updateMicroblogsEntry(
				microblogsEntryId, content, socialRelationType, serviceContext);
		}
		else {
			MicroblogsEntryServiceUtil.addMicroblogsEntry(
				themeDisplay.getUserId(), content, type, receiverUserId,
				receiverMicroblogsEntryId, socialRelationType, serviceContext);
		}
	}

	protected String[] getAssetTagNames(String content) {
		List<String> assetTagNames = new ArrayList<String>();

		Matcher matcher = _assetTagPattern.matcher(content);

		while (matcher.find()) {
			String assetTagName = matcher.group();

			assetTagName = assetTagName.substring(1);

			assetTagNames.add(assetTagName);
		}

		matcher = _userTagPattern.matcher(content);

		while (matcher.find()) {
			String assetTagName = matcher.group();

			assetTagName = assetTagName.replace("[@", StringPool.BLANK);
			assetTagName = assetTagName.replace("]", StringPool.BLANK);

			assetTagNames.add(assetTagName);
		}

		return assetTagNames.toArray(new String[assetTagNames.size()]);
	}

	private Pattern _assetTagPattern = Pattern.compile("\\#\\S*");
	private Pattern _userTagPattern = Pattern.compile("\\[\\@\\S*\\]");

}