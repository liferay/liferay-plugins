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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.util.List;

/**
 * <a href="KaleoTaskInstanceTokenUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceTokenPersistence
 * @see       KaleoTaskInstanceTokenPersistenceImpl
 * @generated
 */
public class KaleoTaskInstanceTokenUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(KaleoTaskInstanceToken)
	 */
	public static void clearCache(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		getPersistence().clearCache(kaleoTaskInstanceToken);
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
	public static List<KaleoTaskInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskInstanceToken remove(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		return getPersistence().remove(kaleoTaskInstanceToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskInstanceToken update(
		KaleoTaskInstanceToken kaleoTaskInstanceToken, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskInstanceToken, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		getPersistence().cacheResult(kaleoTaskInstanceToken);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> kaleoTaskInstanceTokens) {
		getPersistence().cacheResult(kaleoTaskInstanceTokens);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken create(
		long kaleoTaskInstanceTokenId) {
		return getPersistence().create(kaleoTaskInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken remove(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence().remove(kaleoTaskInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskInstanceToken, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence().findByPrimaryKey(kaleoTaskInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskInstanceTokenId,
			companyId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByC_G_ACN_ACP_CD(
		long companyId, long groupId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD(companyId, groupId, assigneeClassName,
			assigneeClassPK, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByC_G_ACN_ACP_CD(
		long companyId, long groupId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean completed, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD(companyId, groupId, assigneeClassName,
			assigneeClassPK, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByC_G_ACN_ACP_CD(
		long companyId, long groupId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD(companyId, groupId, assigneeClassName,
			assigneeClassPK, completed, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByC_G_ACN_ACP_CD_First(
		long companyId, long groupId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD_First(companyId, groupId,
			assigneeClassName, assigneeClassPK, completed, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByC_G_ACN_ACP_CD_Last(
		long companyId, long groupId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD_Last(companyId, groupId,
			assigneeClassName, assigneeClassPK, completed, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByC_G_ACN_ACP_CD_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId, long groupId,
		java.lang.String assigneeClassName, long assigneeClassPK,
		boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByC_G_ACN_ACP_CD_PrevAndNext(kaleoTaskInstanceTokenId,
			companyId, groupId, assigneeClassName, assigneeClassPK, completed,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoInstanceId(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_PrevAndNext(kaleoTaskInstanceTokenId,
			kaleoInstanceId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByC_G_ACN_ACP_CD(long companyId, long groupId,
		java.lang.String assigneeClassName, long assigneeClassPK,
		boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_G_ACN_ACP_CD(companyId, groupId, assigneeClassName,
			assigneeClassPK, completed);
	}

	public static void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoInstanceId(kaleoInstanceId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByC_G_ACN_ACP_CD(long companyId, long groupId,
		java.lang.String assigneeClassName, long assigneeClassPK,
		boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_G_ACN_ACP_CD(companyId, groupId,
			assigneeClassName, assigneeClassPK, completed);
	}

	public static int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoInstanceId(kaleoInstanceId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskInstanceTokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskInstanceTokenPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceTokenPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoTaskInstanceTokenPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoTaskInstanceTokenPersistence _persistence;
}