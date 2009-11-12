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

package com.liferay.socialnetworking.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.socialnetworking.model.MeetupsRegistration;
import com.liferay.socialnetworking.service.MeetupsEntryLocalService;
import com.liferay.socialnetworking.service.MeetupsRegistrationLocalService;
import com.liferay.socialnetworking.service.WallEntryLocalService;
import com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence;
import com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.socialnetworking.service.persistence.WallEntryFinder;
import com.liferay.socialnetworking.service.persistence.WallEntryPersistence;

import java.util.List;

/**
 * <a href="MeetupsRegistrationLocalServiceBaseImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class MeetupsRegistrationLocalServiceBaseImpl
	implements MeetupsRegistrationLocalService {
	public MeetupsRegistration addMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration.setNew(true);

		return meetupsRegistrationPersistence.update(meetupsRegistration, false);
	}

	public MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		return meetupsRegistrationPersistence.create(meetupsRegistrationId);
	}

	public void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws PortalException, SystemException {
		meetupsRegistrationPersistence.remove(meetupsRegistrationId);
	}

	public void deleteMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistrationPersistence.remove(meetupsRegistration);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return meetupsRegistrationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return meetupsRegistrationPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId) throws PortalException, SystemException {
		return meetupsRegistrationPersistence.findByPrimaryKey(meetupsRegistrationId);
	}

	public List<MeetupsRegistration> getMeetupsRegistrations(int start, int end)
		throws SystemException {
		return meetupsRegistrationPersistence.findAll(start, end);
	}

	public int getMeetupsRegistrationsCount() throws SystemException {
		return meetupsRegistrationPersistence.countAll();
	}

	public MeetupsRegistration updateMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration.setNew(false);

		return meetupsRegistrationPersistence.update(meetupsRegistration, true);
	}

	public MeetupsRegistration updateMeetupsRegistration(
		MeetupsRegistration meetupsRegistration, boolean merge)
		throws SystemException {
		meetupsRegistration.setNew(false);

		return meetupsRegistrationPersistence.update(meetupsRegistration, merge);
	}

	public MeetupsEntryLocalService getMeetupsEntryLocalService() {
		return meetupsEntryLocalService;
	}

	public void setMeetupsEntryLocalService(
		MeetupsEntryLocalService meetupsEntryLocalService) {
		this.meetupsEntryLocalService = meetupsEntryLocalService;
	}

	public MeetupsEntryPersistence getMeetupsEntryPersistence() {
		return meetupsEntryPersistence;
	}

	public void setMeetupsEntryPersistence(
		MeetupsEntryPersistence meetupsEntryPersistence) {
		this.meetupsEntryPersistence = meetupsEntryPersistence;
	}

	public MeetupsRegistrationLocalService getMeetupsRegistrationLocalService() {
		return meetupsRegistrationLocalService;
	}

	public void setMeetupsRegistrationLocalService(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {
		this.meetupsRegistrationLocalService = meetupsRegistrationLocalService;
	}

	public MeetupsRegistrationPersistence getMeetupsRegistrationPersistence() {
		return meetupsRegistrationPersistence;
	}

	public void setMeetupsRegistrationPersistence(
		MeetupsRegistrationPersistence meetupsRegistrationPersistence) {
		this.meetupsRegistrationPersistence = meetupsRegistrationPersistence;
	}

	public WallEntryLocalService getWallEntryLocalService() {
		return wallEntryLocalService;
	}

	public void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
		this.wallEntryLocalService = wallEntryLocalService;
	}

	public WallEntryPersistence getWallEntryPersistence() {
		return wallEntryPersistence;
	}

	public void setWallEntryPersistence(
		WallEntryPersistence wallEntryPersistence) {
		this.wallEntryPersistence = wallEntryPersistence;
	}

	public WallEntryFinder getWallEntryFinder() {
		return wallEntryFinder;
	}

	public void setWallEntryFinder(WallEntryFinder wallEntryFinder) {
		this.wallEntryFinder = wallEntryFinder;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.socialnetworking.service.MeetupsEntryLocalService")
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence")
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.MeetupsRegistrationLocalService")
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence")
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.WallEntryLocalService")
	protected WallEntryLocalService wallEntryLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.WallEntryPersistence")
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.WallEntryFinder")
	protected WallEntryFinder wallEntryFinder;
}