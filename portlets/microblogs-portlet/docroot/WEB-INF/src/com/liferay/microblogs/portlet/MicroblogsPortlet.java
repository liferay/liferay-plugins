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

package com.liferay.microblogs.portlet;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
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

		long userId = themeDisplay.getUserId();

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		String content = ParamUtil.getString(actionRequest, "content");
		int type = ParamUtil.getInteger(actionRequest, "type");
		long receiverUserId = ParamUtil.getLong(
			actionRequest, "receiverUserId");
		long receiverEntryId = ParamUtil.getLong(
			actionRequest, "receiverEntryId");
		int socialRelationType = ParamUtil.getInteger(
			actionRequest, "socialRelationType");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MicroblogsEntry.class.getName(), actionRequest);

		serviceContext.setAssetTagNames(getTags(content));

		if (microblogsEntryId > 0) {
			MicroblogsEntryServiceUtil.updateMicroblogsEntry(
				microblogsEntryId, content, socialRelationType, serviceContext);
		}
		else {
			MicroblogsEntryServiceUtil.addMicroblogsEntry(
				userId, content, type, receiverUserId, receiverEntryId,
				socialRelationType, serviceContext);
		}
	}

	protected String[] getTags(String content) {
		List<String> assetTagNamesList = new ArrayList();

		Pattern pattern = Pattern.compile("#\\w+");
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			String matchedString = matcher.group();
			String assetTagName = matchedString.substring(1);

			assetTagNamesList.add(assetTagName);
		}

		String[] assetTagNames = new String[assetTagNamesList.size()];

		assetTagNames = assetTagNamesList.toArray(assetTagNames);

		return assetTagNames;
	}

}