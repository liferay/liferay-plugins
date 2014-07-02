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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.model.KBTemplateSearchDisplay;
import com.liferay.knowledgebase.model.impl.KBTemplateSearchDisplayImpl;
import com.liferay.knowledgebase.service.base.KBTemplateServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.DisplayPermission;
import com.liferay.knowledgebase.service.permission.KBTemplatePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateServiceImpl extends KBTemplateServiceBaseImpl {

	@Override
	public KBTemplate addKBTemplate(
			String portletId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_TEMPLATE);
		}
		else if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			DisplayPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_TEMPLATE);
		}

		return kbTemplateLocalService.addKBTemplate(
			getUserId(), title, content, serviceContext);
	}

	@Override
	public KBTemplate deleteKBTemplate(long kbTemplateId)
		throws PortalException, SystemException {

		KBTemplatePermission.check(
			getPermissionChecker(), kbTemplateId, ActionKeys.DELETE);

		return kbTemplateLocalService.deleteKBTemplate(kbTemplateId);
	}

	@Override
	public void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.DELETE_KB_TEMPLATES);

		kbTemplateLocalService.deleteKBTemplates(kbTemplateIds);
	}

	@Override
	public List<KBTemplate> getGroupKBTemplates(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kbTemplatePersistence.filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupKBTemplatesCount(long groupId) throws SystemException {
		return kbTemplatePersistence.filterCountByGroupId(groupId);
	}

	@Override
	public KBTemplate getKBTemplate(long kbTemplateId)
		throws PortalException, SystemException {

		KBTemplatePermission.check(
			getPermissionChecker(), kbTemplateId, ActionKeys.VIEW);

		return kbTemplateLocalService.getKBTemplate(kbTemplateId);
	}

	@Override
	public KBTemplateSearchDisplay getKBTemplateSearchDisplay(
			long groupId, String title, String content, Date startDate,
			Date endDate, boolean andOperator, int[] curStartValues, int cur,
			int delta, OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		// See LPS-9546

		int start = 0;

		if (curStartValues.length > (cur - SearchContainer.DEFAULT_CUR)) {
			start = curStartValues[cur - SearchContainer.DEFAULT_CUR];

			curStartValues = ArrayUtil.subset(
				curStartValues, 0, cur - SearchContainer.DEFAULT_CUR + 1);
		}
		else {
			cur = SearchContainer.DEFAULT_CUR;

			curStartValues = new int[] {0};
		}

		int end = start + _INTERVAL;

		List<KBTemplate> kbTemplates = new ArrayList<KBTemplate>();

		int curStartValue = 0;

		while (curStartValue == 0) {
			List<KBTemplate> curKBTemplates = kbTemplateLocalService.search(
				groupId, title, content, startDate, endDate, andOperator, start,
				end, orderByComparator);

			if (curKBTemplates.isEmpty()) {
				break;
			}

			for (int i = 0; i < curKBTemplates.size(); i++) {
				KBTemplate curKBTemplate = curKBTemplates.get(i);

				if (!KBTemplatePermission.contains(
						getPermissionChecker(), curKBTemplate,
						ActionKeys.VIEW)) {

					continue;
				}

				if (kbTemplates.size() == delta) {
					curStartValue = start + i;

					break;
				}

				kbTemplates.add(curKBTemplate);
			}

			start = start + _INTERVAL;
			end = start + _INTERVAL;
		}

		int total = ((cur - 1) * delta) + kbTemplates.size();

		if (curStartValue > 0) {
			curStartValues = ArrayUtil.append(curStartValues, curStartValue);

			total = total + 1;
		}

		return new KBTemplateSearchDisplayImpl(
			kbTemplates, total, curStartValues);
	}

	@Override
	public KBTemplate updateKBTemplate(
			long kbTemplateId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KBTemplatePermission.check(
			getPermissionChecker(), kbTemplateId, ActionKeys.UPDATE);

		return kbTemplateLocalService.updateKBTemplate(
			kbTemplateId, title, content, serviceContext);
	}

	private static final int _INTERVAL = 200;

}