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

import com.liferay.knowledgebase.model.KBStructure;
import com.liferay.knowledgebase.model.KBStructureField;
import com.liferay.knowledgebase.model.KBStructureSearchDisplay;
import com.liferay.knowledgebase.model.impl.KBStructureSearchDisplayImpl;
import com.liferay.knowledgebase.service.base.KBStructureServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.DisplayPermission;
import com.liferay.knowledgebase.service.permission.KBStructurePermission;
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
 * @author Brian Wing Shun Chan
 */
public class KBStructureServiceImpl extends KBStructureServiceBaseImpl {

	public KBStructure addKBStructure(
			String portletId, String localizedLanguageId, String title,
			List<KBStructureField> kbStructureFields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			AdminPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_STRUCTURE);
		}
		else if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			DisplayPermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				ActionKeys.ADD_KB_STRUCTURE);
		}

		return kbStructureLocalService.addKBStructure(
			getUserId(), localizedLanguageId, title, kbStructureFields,
			serviceContext);
	}

	public void deleteKBStructure(long kbStructureId)
		throws PortalException, SystemException {

		KBStructurePermission.check(
			getPermissionChecker(), kbStructureId, ActionKeys.DELETE);

		kbStructureLocalService.deleteKBStructure(kbStructureId);
	}

	public void deleteKBStructureLocalization(
			long kbStructureId, String localizedLanguageId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KBStructurePermission.check(
			getPermissionChecker(), kbStructureId, ActionKeys.DELETE);

		kbStructureLocalService.deleteKBStructureLocalization(
			kbStructureId, localizedLanguageId, serviceContext);
	}

	public void deleteKBStructures(long groupId, long[] kbStructureIds)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.DELETE_KB_STRUCTURES);

		kbStructureLocalService.deleteKBStructures(kbStructureIds);
	}

	public List<KBStructure> getGroupKBStructures(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kbStructurePersistence.filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	public int getGroupKBStructuresCount(long groupId) throws SystemException {
		return kbStructurePersistence.filterCountByGroupId(groupId);
	}

	public KBStructure getKBStructure(long kbStructureId)
		throws PortalException, SystemException {

		KBStructurePermission.check(
			getPermissionChecker(), kbStructureId, ActionKeys.VIEW);

		return kbStructureLocalService.getKBStructure(kbStructureId);
	}

	public KBStructureSearchDisplay getKBStructureSearchDisplay(
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

		List<KBStructure> kbStructures = new ArrayList<KBStructure>();

		int curStartValue = 0;

		while (curStartValue == 0) {
			List<KBStructure> curKBStructures = kbStructureLocalService.search(
				groupId, title, content, startDate, endDate, andOperator, start,
				end, orderByComparator);

			if (curKBStructures.isEmpty()) {
				break;
			}

			for (int i = 0; i < curKBStructures.size(); i++) {
				KBStructure curKBStructure = curKBStructures.get(i);

				if (!KBStructurePermission.contains(
						getPermissionChecker(), curKBStructure,
						ActionKeys.VIEW)) {

					continue;
				}

				if (kbStructures.size() == delta) {
					curStartValue = start + i;

					break;
				}

				kbStructures.add(curKBStructure);
			}

			start = start + _INTERVAL;
			end = start + _INTERVAL;
		}

		int total = ((cur - 1) * delta) + kbStructures.size();

		if (curStartValue > 0) {
			curStartValues = ArrayUtil.append(curStartValues, curStartValue);

			total = total + 1;
		}

		return new KBStructureSearchDisplayImpl(
			kbStructures, total, curStartValues);
	}

	public KBStructure updateKBStructure(
			long kbStructureId, String localizedLanguageId, String title,
			List<KBStructureField> kbStructureFields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KBStructurePermission.check(
			getPermissionChecker(), kbStructureId, ActionKeys.UPDATE);

		return kbStructureLocalService.updateKBStructure(
			kbStructureId, localizedLanguageId, title, kbStructureFields,
			serviceContext);
	}

	private static final int _INTERVAL = 200;

}