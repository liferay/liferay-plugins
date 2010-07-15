/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceTokenPersistence
 * @see       KaleoInstanceTokenPersistenceImpl
 * @generated
 */
public class KaleoInstanceTokenUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoInstanceToken kaleoInstanceToken) {
		getPersistence().clearCache(kaleoInstanceToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoInstanceToken remove(
		KaleoInstanceToken kaleoInstanceToken) throws SystemException {
		return getPersistence().remove(kaleoInstanceToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoInstanceToken update(
		KaleoInstanceToken kaleoInstanceToken, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoInstanceToken, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoInstanceToken update(
		KaleoInstanceToken kaleoInstanceToken, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoInstanceToken, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken) {
		getPersistence().cacheResult(kaleoInstanceToken);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> kaleoInstanceTokens) {
		getPersistence().cacheResult(kaleoInstanceTokens);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken create(
		long kaleoInstanceTokenId) {
		return getPersistence().create(kaleoInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken remove(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence().remove(kaleoInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoInstanceToken, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByPrimaryKey(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence().findByPrimaryKey(kaleoInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByPrimaryKey(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoInstanceTokenId,
			companyId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoInstanceTokenId,
			kaleoDefinitionId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoInstanceId(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_PrevAndNext(kaleoInstanceTokenId,
			kaleoInstanceId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId, start,
			end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId, start,
			end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_First(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_First(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_Last(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_Last(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_PrevAndNext(kaleoInstanceTokenId, companyId,
			parentKaleoInstanceTokenId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_First(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_First(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_Last(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_Last(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_PrevAndNext(kaleoInstanceTokenId,
			companyId, parentKaleoInstanceTokenId, completionDate,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoInstanceId(kaleoInstanceId);
	}

	public static void removeByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	public static void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoInstanceId(kaleoInstanceId);
	}

	public static int countByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	public static int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoInstanceTokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoInstanceTokenPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoInstanceTokenPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoInstanceTokenPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoInstanceTokenPersistence _persistence;
}