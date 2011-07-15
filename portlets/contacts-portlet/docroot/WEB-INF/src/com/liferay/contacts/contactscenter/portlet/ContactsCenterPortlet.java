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

package com.liferay.contacts.contactscenter.portlet;

import com.liferay.contacts.util.ContactsUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.ServletResponseUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class ContactsCenterPortlet extends MVCPortlet {

	public void addSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(actionRequest, "userId");
		int type = ParamUtil.getInteger(actionRequest, "type");

		boolean blocked = SocialRelationLocalServiceUtil.hasRelation(
			userId, themeDisplay.getUserId(),
			SocialRelationConstants.TYPE_UNI_ENEMY);

		if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
			SocialRelationLocalServiceUtil.deleteRelations(
				themeDisplay.getUserId(), userId);
		}
		else if (blocked) {
			return;
		}

		SocialRelationLocalServiceUtil.addRelation(
			themeDisplay.getUserId(), userId, type);

		if (blocked) {
			SocialRelationLocalServiceUtil.addRelation(
				userId, themeDisplay.getUserId(), type);
		}
	}

	public void deleteSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(actionRequest, "userId");
		int type = ParamUtil.getInteger(actionRequest, "type");

		SocialRelationLocalServiceUtil.deleteRelation(
			themeDisplay.getUserId(), userId, type);
	}

	public void exportVCard(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		User user = UserLocalServiceUtil.getUserById(userId);

		String vCard = ContactsUtil.getVCard(user);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			request, response, user.getFullName() + ".vcf",
			vCard.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void exportVCards(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long userId = ParamUtil.getLong(resourceRequest, "userId");
		int socialRelationType = ParamUtil.getInteger(
			resourceRequest, "socialRelationType");

		List<User> users = UserLocalServiceUtil.getSocialUsers(
			userId, socialRelationType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);

		String vCards = ContactsUtil.getVCards(users);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			request, response, "vcards.vcf",
			vCards.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void requestSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(actionRequest, "userId");
		int type = ParamUtil.getInteger(actionRequest, "type");

		if (SocialRelationLocalServiceUtil.hasRelation(
				userId, themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			return;
		}

		SocialRequestLocalServiceUtil.addRequest(
			themeDisplay.getUserId(), 0, User.class.getName(),
			themeDisplay.getUserId(), type, StringPool.BLANK, userId);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String id = resourceRequest.getResourceID();

			if (id.equals("exportVCard")) {
				exportVCard(resourceRequest, resourceResponse);
			}
			else if (id.equals("exportVCards")) {
				exportVCards(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateSocialRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long requestId = ParamUtil.getLong(actionRequest, "requestId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		SocialRequest socialRequest =
			SocialRequestLocalServiceUtil.getSocialRequest(requestId);

		if (SocialRelationLocalServiceUtil.hasRelation(
				socialRequest.getReceiverUserId(), socialRequest.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			status = SocialRequestConstants.STATUS_IGNORE;
		}

		SocialRequestLocalServiceUtil.updateRequest(
			requestId, status, themeDisplay);
	}

}