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
import com.liferay.portlet.messageboards.util.comparator.MessageCreateDateComparator;
import com.liferay.screens.service.base.ScreensCommentServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the screens comment remote service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.screens.service.ScreensCommentService} interface.
 * <p/>
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author José Manuel Navarro
 * @see com.liferay.screens.service.base.ScreensCommentServiceBaseImpl
 * @see com.liferay.screens.service.ScreensCommentServiceUtil
 */
public class ScreensCommentServiceImpl extends ScreensCommentServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.screens.service.ScreensCommentServiceUtil} to access the screens comment remote service.
	 */

	@Override
	public JSONObject addComment(String className, long classPK, String body)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);
		Group group = groupLocalService.getGroup(assetEntry.getGroupId());

		checkPermission(
			_checkPermissionDiscussion, getPermissionChecker(),
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

		checkPermission(
			_checkPermissionMessage, getPermissionChecker(), commentId,
			ActionKeys.VIEW);

		return toJSONObject(mbMessage);
	}

	@Override
	public JSONArray getComments(
			String className, long classPK, int start, int end)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);

		Group group = groupLocalService.getGroup(assetEntry.getGroupId());

		checkPermission(
			_checkPermissionDiscussion, getPermissionChecker(),
			group.getCompanyId(), group.getGroupId(), className, classPK,
			getUserId(), ActionKeys.VIEW);

		List<MBMessage> mbMessages =
			mbMessageLocalService.getUserDiscussionMessages(
				getUserId(), classNameLocalService.getClassNameId(className),
				classPK, WorkflowConstants.STATUS_APPROVED, start, end,
				new MessageCreateDateComparator(false));

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (MBMessage mbMessage : mbMessages) {
			jsonArray.put(toJSONObject(mbMessage));
		}

		return jsonArray;
	}

	@Override
	public int getCommentsCount(String className, long classPK)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			className, classPK);

		Group group = groupLocalService.getGroup(assetEntry.getGroupId());

		checkPermission(
			_checkPermissionDiscussion, getPermissionChecker(),
			group.getCompanyId(), group.getGroupId(), className, classPK,
			getUserId(), ActionKeys.VIEW);

		return mbMessageLocalService.getDiscussionMessagesCount(
			className, classPK, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public JSONObject updateComment(long commentId, String body)
		throws PortalException, SystemException {

		checkPermission(
			_checkPermissionMessage, getPermissionChecker(), commentId,
			ActionKeys.UPDATE);

		MBMessage mbMessage = mbMessageLocalService.getMBMessage(commentId);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		mbMessage = mbMessageLocalService.updateDiscussionMessage(
			getUserId(), mbMessage.getMessageId(), mbMessage.getClassName(),
			mbMessage.getClassPK(), StringPool.BLANK, body, serviceContext);

		return toJSONObject(mbMessage);
	}

	protected Object checkPermission(MethodKey methodKey, Object... args)
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
		jsonObject.put("createDate", comment.getCreateDate().getTime());
		jsonObject.put("modifiedDate", comment.getModifiedDate().getTime());
		jsonObject.put("userId", comment.getUserId());
		jsonObject.put("userName", comment.getUserName());

		boolean deletePermission = (Boolean)checkPermission(
			_containsPermissionMessage, getPermissionChecker(),
			comment.getMessageId(), ActionKeys.DELETE);
		boolean updatePermission = (Boolean)checkPermission(
			_containsPermissionMessage, getPermissionChecker(),
			comment.getMessageId(), ActionKeys.UPDATE);
		jsonObject.put("updatePermission", updatePermission);
		jsonObject.put("deletePermission", deletePermission);
		return jsonObject;
	}

	private MBMessage addComment(
			long userId, String fullName, long groupId, String className,
			long classPK, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessageDisplay messageDisplay =
			mbMessageLocalService.getDiscussionMessageDisplay(
				userId, groupId, className, classPK,
				WorkflowConstants.STATUS_APPROVED);

		MBThread thread = messageDisplay.getThread();

		List<MBMessage> messages = mbMessageLocalService.getThreadMessages(
			thread.getThreadId(), WorkflowConstants.STATUS_APPROVED);

		for (MBMessage message : messages) {
			String messageBody = message.getBody();

			if (messageBody.equals(body)) {
				throw new SystemException(body);
			}
		}

		return mbMessageLocalService.addDiscussionMessage(
			userId, fullName, groupId, className, classPK, thread.getThreadId(),
			thread.getRootMessageId(), StringPool.BLANK, body, serviceContext);
	}

	private static final MethodKey _checkPermissionDiscussion =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
				"MBDiscussionPermission"),
			"check", PermissionChecker.class, long.class, long.class,
			String.class, long.class, long.class, String.class);
	private static final MethodKey _checkPermissionMessage =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
				"MBMessagePermission"),
			"check", PermissionChecker.class, long.class, String.class);
	private static final MethodKey _containsPermissionMessage =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.messageboards.service.permission." +
				"MBMessagePermission"),
			"contains", PermissionChecker.class, long.class, String.class);

	private static Log _log = LogFactoryUtil.getLog(
		ScreensCommentServiceImpl.class);

}