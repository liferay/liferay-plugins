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

package com.liferay.knowledgebase.service;

/**
 * <p>
 * This class is a wrapper for {@link KBStructureService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBStructureService
 * @generated
 */
public class KBStructureServiceWrapper implements KBStructureService {
	public KBStructureServiceWrapper(KBStructureService kbStructureService) {
		_kbStructureService = kbStructureService;
	}

	public com.liferay.knowledgebase.model.KBStructure addKBStructure(
		java.lang.String portletId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.addKBStructure(portletId,
			localizedLanguageId, title, kbStructureFields, serviceContext);
	}

	public void deleteKBStructure(long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureService.deleteKBStructure(kbStructureId);
	}

	public void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureService.deleteKBStructureLocalization(kbStructureId,
			localizedLanguageId, serviceContext);
	}

	public void deleteKBStructures(long groupId, long[] kbStructureIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureService.deleteKBStructures(groupId, kbStructureIds);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBStructure> getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.getGroupKBStructures(groupId, start, end,
			orderByComparator);
	}

	public int getGroupKBStructuresCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.getGroupKBStructuresCount(groupId);
	}

	public com.liferay.knowledgebase.model.KBStructure getKBStructure(
		long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.getKBStructure(kbStructureId);
	}

	public com.liferay.knowledgebase.model.KBStructureSearchDisplay getKBStructureSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.getKBStructureSearchDisplay(groupId, title,
			content, startDate, endDate, andOperator, curStartValues, cur,
			delta, orderByComparator);
	}

	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureService.updateKBStructure(kbStructureId,
			localizedLanguageId, title, kbStructureFields, serviceContext);
	}

	public KBStructureService getWrappedKBStructureService() {
		return _kbStructureService;
	}

	public void setWrappedKBStructureService(
		KBStructureService kbStructureService) {
		_kbStructureService = kbStructureService;
	}

	private KBStructureService _kbStructureService;
}