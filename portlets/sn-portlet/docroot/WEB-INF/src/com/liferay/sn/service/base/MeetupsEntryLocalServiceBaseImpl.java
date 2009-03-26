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

package com.liferay.sn.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.sn.model.MeetupsEntry;
import com.liferay.sn.service.MeetupsEntryLocalService;
import com.liferay.sn.service.MeetupsRegistrationLocalService;
import com.liferay.sn.service.WallEntryLocalService;
import com.liferay.sn.service.persistence.MeetupsEntryPersistence;
import com.liferay.sn.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.sn.service.persistence.WallEntryFinder;
import com.liferay.sn.service.persistence.WallEntryPersistence;

import java.util.List;

/**
 * <a href="MeetupsEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class MeetupsEntryLocalServiceBaseImpl
	implements MeetupsEntryLocalService {
	public MeetupsEntry addMeetupsEntry(MeetupsEntry meetupsEntry)
		throws SystemException {
		meetupsEntry.setNew(true);

		return meetupsEntryPersistence.update(meetupsEntry, false);
	}

	public MeetupsEntry createMeetupsEntry(long meetupsEntryId) {
		return meetupsEntryPersistence.create(meetupsEntryId);
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws PortalException, SystemException {
		meetupsEntryPersistence.remove(meetupsEntryId);
	}

	public void deleteMeetupsEntry(MeetupsEntry meetupsEntry)
		throws SystemException {
		meetupsEntryPersistence.remove(meetupsEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return meetupsEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return meetupsEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public MeetupsEntry getMeetupsEntry(long meetupsEntryId)
		throws PortalException, SystemException {
		return meetupsEntryPersistence.findByPrimaryKey(meetupsEntryId);
	}

	public List<MeetupsEntry> getMeetupsEntries(int start, int end)
		throws SystemException {
		return meetupsEntryPersistence.findAll(start, end);
	}

	public int getMeetupsEntriesCount() throws SystemException {
		return meetupsEntryPersistence.countAll();
	}

	public MeetupsEntry updateMeetupsEntry(MeetupsEntry meetupsEntry)
		throws SystemException {
		meetupsEntry.setNew(false);

		return meetupsEntryPersistence.update(meetupsEntry, true);
	}

	public MeetupsEntry updateMeetupsEntry(MeetupsEntry meetupsEntry,
		boolean merge) throws SystemException {
		meetupsEntry.setNew(false);

		return meetupsEntryPersistence.update(meetupsEntry, merge);
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

	@BeanReference(name = "com.liferay.sn.service.MeetupsEntryLocalService.impl")
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	@BeanReference(name = "com.liferay.sn.service.persistence.MeetupsEntryPersistence.impl")
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.sn.service.MeetupsRegistrationLocalService.impl")
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	@BeanReference(name = "com.liferay.sn.service.persistence.MeetupsRegistrationPersistence.impl")
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.sn.service.WallEntryLocalService.impl")
	protected WallEntryLocalService wallEntryLocalService;
	@BeanReference(name = "com.liferay.sn.service.persistence.WallEntryPersistence.impl")
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.WallEntryFinder.impl")
	protected WallEntryFinder wallEntryFinder;
}