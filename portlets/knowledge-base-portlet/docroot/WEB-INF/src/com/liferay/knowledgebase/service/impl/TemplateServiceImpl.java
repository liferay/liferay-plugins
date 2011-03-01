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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.model.TemplateSearchDisplay;
import com.liferay.knowledgebase.model.impl.TemplateSearchDisplayImpl;
import com.liferay.knowledgebase.service.base.TemplateServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.TemplatePermission;
import com.liferay.knowledgebase.util.ActionKeys;
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
public class TemplateServiceImpl extends TemplateServiceBaseImpl {

	public Template addTemplate(
			String title, String content, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_TEMPLATE);

		return templateLocalService.addTemplate(
			getUserId(), title, content, description, serviceContext);
	}

	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.DELETE);

		templateLocalService.deleteTemplate(templateId);
	}

	public List<Template> getGroupTemplates(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return templatePersistence.filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	public int getGroupTemplatesCount(long groupId) throws SystemException {
		return templatePersistence.filterCountByGroupId(groupId);
	}

	public Template getTemplate(long templateId)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.VIEW);

		return templateLocalService.getTemplate(templateId);
	}

	public TemplateSearchDisplay getTemplateSearchDisplay(
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

		List<Template> templates = new ArrayList<Template>();

		int curStartValue = 0;

		while (curStartValue == 0) {
			List<Template> curTemplates = templateLocalService.search(
				groupId, title, content, startDate, endDate, andOperator, start,
				end, orderByComparator);

			if (curTemplates.isEmpty()) {
				break;
			}

			for (int i = 0; i < curTemplates.size(); i++) {
				Template curTemplate = curTemplates.get(i);

				if (!TemplatePermission.contains(
						getPermissionChecker(), curTemplate, ActionKeys.VIEW)) {

					continue;
				}

				if (templates.size() == delta) {
					curStartValue = start + i;

					break;
				}

				templates.add(curTemplate);
			}

			start = start + _INTERVAL;
			end = start + _INTERVAL;
		}

		int total = ((cur - 1) * delta) + templates.size();

		if (curStartValue > 0) {
			curStartValues = ArrayUtil.append(curStartValues, curStartValue);

			total = total + 1;
		}

		return new TemplateSearchDisplayImpl(templates, total, curStartValues);
	}

	public Template updateTemplate(
			long templateId, String title, String content, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		return templateLocalService.updateTemplate(
			templateId, title, content, description, serviceContext);
	}

	private static final int _INTERVAL = 200;

}