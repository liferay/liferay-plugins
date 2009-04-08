/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.bi.report.service.persistence;

/**
 * <a href="ReportDefinitionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionUtil {
	public static void cacheResult(
		com.liferay.bi.report.model.ReportDefinition reportDefinition) {
		getPersistence().cacheResult(reportDefinition);
	}

	public static void cacheResult(
		java.util.List<com.liferay.bi.report.model.ReportDefinition> reportDefinitions) {
		getPersistence().cacheResult(reportDefinitions);
	}

	public static com.liferay.bi.report.model.ReportDefinition create(
		long definitionId) {
		return getPersistence().create(definitionId);
	}

	public static com.liferay.bi.report.model.ReportDefinition remove(
		long definitionId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(definitionId);
	}

	public static com.liferay.bi.report.model.ReportDefinition remove(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(reportDefinition);
	}

	public static com.liferay.bi.report.model.ReportDefinition update(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(reportDefinition);
	}

	public static com.liferay.bi.report.model.ReportDefinition update(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(reportDefinition, merge);
	}

	public static com.liferay.bi.report.model.ReportDefinition updateImpl(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(reportDefinition, merge);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByPrimaryKey(
		long definitionId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(definitionId);
	}

	public static com.liferay.bi.report.model.ReportDefinition fetchByPrimaryKey(
		long definitionId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(definitionId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_First(uuid, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_Last(uuid, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByUuid_PrevAndNext(
		long definitionId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_PrevAndNext(definitionId, uuid, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	public static com.liferay.bi.report.model.ReportDefinition fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	public static com.liferay.bi.report.model.ReportDefinition fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByCompanyId_PrevAndNext(
		long definitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(definitionId, companyId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyGroupId(companyId, groupId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId(companyId, groupId, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByCompanyGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_First(companyId, groupId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByCompanyGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_Last(companyId, groupId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByCompanyGroupId_PrevAndNext(
		long definitionId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_PrevAndNext(definitionId, companyId,
			groupId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByGroupId_PrevAndNext(
		long definitionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(definitionId, groupId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByUserId_PrevAndNext(
		long definitionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(definitionId, userId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name) throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name, start, end, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByName_First(name, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByName_Last(name, obc);
	}

	public static com.liferay.bi.report.model.ReportDefinition[] findByName_PrevAndNext(
		long definitionId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		return getPersistence().findByName_PrevAndNext(definitionId, name, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyGroupId(companyId, groupId);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByName(name);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyGroupId(companyId, groupId);
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByName(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByName(name);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static ReportDefinitionPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(ReportDefinitionPersistence persistence) {
		_persistence = persistence;
	}

	private static ReportDefinitionPersistence _persistence;
}