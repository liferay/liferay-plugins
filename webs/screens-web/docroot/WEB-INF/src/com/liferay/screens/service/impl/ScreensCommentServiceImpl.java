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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.MBTreeWalker;
import com.liferay.screens.service.base.ScreensCommentServiceBaseImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author José Manuel Navarro
 */
public class ScreensCommentServiceImpl extends ScreensCommentServiceBaseImpl {

	@Override
	public JSONObject addComment(String className, long classPK, String body)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);

		Group group = groupService.getGroup(assetEntry.getGroupId());

		invoke(
			_mbDiscussionPermissionCheckMethodKey, getPermissionChecker(),
			group.getCompanyId(), group.getGroupId(), className, classPK,
			getUserId(), ActionKeys.ADD_DISCUSSION);

		MBMessage mbMessage = addComment(
			getUserId(), getUser().getFullName(), group.getGroupId(), className,
			classPK, body, new ServiceContext());

		return toJSONObject(mbMessage);
	}

	@Override
	public JSONObject getComment(long commentId)
		throws PortalException, SystemException {

		MBMessage mbMessage = mbMessageLocalService.getMBMessage(commentId);

		invoke(
			_mbMessagePermissionCheckMethodKey, getPermissionChecker(),
			commentId, ActionKeys.VIEW);

		return toJSONObject(mbMessage);
	}

	@Override
	public JSONArray getComments(
			String className, long classPK, int start, int end)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);

		Group group = groupService.getGroup(assetEntry.getGroupId());

		invoke(
			_mbDiscussionPermissionCheckMethodKey, getPermissionChecker(),
			group.getCompanyId(), group.getGroupId(), className, classPK,
			getUserId(), ActionKeys.VIEW);

		MBMessageDisplay mbMessageDisplay =
			mbMessageLocalService.getDiscussionMessageDisplay(
				getUserId(), assetEntry.getGroupId(), className, classPK,
				WorkflowConstants.STATUS_APPROVED);

		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		MBTreeWalker mbTreeWalker = mbMessageDisplay.getTreeWalker();

		List<MBMessage> mbMessages = mbTreeWalker.getMessages();

		Iterator<MBMessage> iterator = mbMessages.listIterator(start);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (end == QueryUtil.ALL_POS) {
			while (iterator.hasNext()) {
				MBMessage mbMessage = iterator.next();

				if (!mbMessage.isRoot()) {
					JSONObject jsonObject = toJSONObject(mbMessage);

					jsonArray.put(jsonObject);
				}
			}
		}
		else {
			int commentsCount = end - start;

			while (iterator.hasNext() && (commentsCount > 0)) {
				MBMessage mbMessage = iterator.next();

				if (!mbMessage.isRoot()) {
					JSONObject jsonObject = toJSONObject(mbMessage);

					jsonArray.put(jsonObject);

					commentsCount--;
				}
			}
		}

		return jsonArray;
	}

	@Override
	public int getCommentsCount(String className, long classPK)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);

		Group group = groupService.getGroup(assetEntry.getGroupId());

		invoke(
			_mbDiscussionPermissionCheckMethodKey, getPermissionChecker(),
			group.getCompanyId(), group.getGroupId(), className, classPK,
			getUserId(), ActionKeys.VIEW);

		return mbMessageLocalService.getDiscussionMessagesCount(
			className, classPK, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public JSONObject updateComment(long commentId, String body)
		throws PortalException, SystemException {

		invoke(
			_mbMessagePermissionCheckMethodKey, getPermissionChecker(),
			commentId, ActionKeys.UPDATE);

		MBMessage mbMessage = mbMessageLocalService.getMBMessage(commentId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		mbMessage = mbMessageLocalService.updateDiscussionMessage(
			getUserId(), mbMessage.getMessageId(), mbMessage.getClassName(),
			mbMessage.getClassPK(), StringPool.BLANK, body, serviceContext);

		return toJSONObject(mbMessage);
	}

	protected MBMessage addComment(
			long userId, String fullName, long groupId, String className,
			long classPK, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessageDisplay mbMessageDisplay =
			mbMessageLocalService.getDiscussionMessageDisplay(
				userId, groupId, className, classPK,
				WorkflowConstants.STATUS_APPROVED);

		MBThread mbThread = mbMessageDisplay.getThread();

		List<MBMessage> mbMessages = mbMessageLocalService.getThreadMessages(
			mbThread.getThreadId(), WorkflowConstants.STATUS_APPROVED);

		for (MBMessage mbMessage : mbMessages) {
			String mbMessageBody = mbMessage.getBody();

			if (mbMessageBody.equals(body)) {
				throw new SystemException(body);
			}
		}

		return mbMessageLocalService.addDiscussionMessage(
			userId, fullName, groupId, className, classPK,
			mbThread.getThreadId(), mbThread.getRootMessageId(),
			StringPool.BLANK, body, serviceContext);
	}

	protected Object invoke(MethodKey methodKey, Object... args)
		throws PortalException, SystemException {

		try {
			return PortalClassInvoker.invoke(false, methodKey, args);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected JSONObject toJSONObject(MBMessage comment)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("body", comment.getBody());
		jsonObject.put("commentId", comment.getMessageId());

		Date createDate = comment.getCreateDate();

		jsonObject.put("createDate", createDate.getTime());

		boolean deletePermission = (Boolean)invoke(
			_mbMessagePermissionContainsMethodKey, getPermissionChecker(),
			comment.getMessageId(), ActionKeys.DELETE);

		jsonObject.put("deletePermission", deletePermission);

		Date modifiedDate = comment.getModifiedDate();

		jsonObject.put("modifiedDate", modifiedDate.getTime());

		boolean updatePermission = (Boolean)invoke(
			_mbMessagePermissionContainsMethodKey, getPermissionChecker(),
			comment.getMessageId(), ActionKeys.UPDATE);

		jsonObject.put("updatePermission", updatePermission);

		jsonObject.put("userId", comment.getUserId());
		jsonObject.put("userName", comment.getUserName());

		return jsonObject;
	}

	private static final MethodKey _mbDiscussionPermissionCheckMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
					"MBDiscussionPermission"),
			"check", PermissionChecker.class, long.class, long.class,
			String.class, long.class, long.class, String.class);
	private static final MethodKey _mbMessagePermissionCheckMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
					"MBMessagePermission"),
			"check", PermissionChecker.class, long.class, String.class);
	private static final MethodKey _mbMessagePermissionContainsMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
					"MBMessagePermission"),
			"contains", PermissionChecker.class, long.class, String.class);

	private static Log _log = LogFactoryUtil.getLog(
		ScreensCommentServiceImpl.class);

}