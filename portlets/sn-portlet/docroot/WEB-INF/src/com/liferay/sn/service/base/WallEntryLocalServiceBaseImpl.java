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

import com.liferay.sn.model.WallEntry;
import com.liferay.sn.service.MeetupsEntryLocalService;
import com.liferay.sn.service.MeetupsRegistrationLocalService;
import com.liferay.sn.service.WallEntryLocalService;
import com.liferay.sn.service.persistence.MeetupsEntryPersistence;
import com.liferay.sn.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.sn.service.persistence.WallEntryFinder;
import com.liferay.sn.service.persistence.WallEntryPersistence;

import java.util.List;

/**
 * <a href="WallEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class WallEntryLocalServiceBaseImpl
	implements WallEntryLocalService {
	public WallEntry addWallEntry(WallEntry wallEntry)
		throws SystemException {
		wallEntry.setNew(true);

		return wallEntryPersistence.update(wallEntry, false);
	}

	public WallEntry createWallEntry(long wallEntryId) {
		return wallEntryPersistence.create(wallEntryId);
	}

	public void deleteWallEntry(long wallEntryId)
		throws PortalException, SystemException {
		wallEntryPersistence.remove(wallEntryId);
	}

	public void deleteWallEntry(WallEntry wallEntry) throws SystemException {
		wallEntryPersistence.remove(wallEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return wallEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return wallEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public WallEntry getWallEntry(long wallEntryId)
		throws PortalException, SystemException {
		return wallEntryPersistence.findByPrimaryKey(wallEntryId);
	}

	public List<WallEntry> getWallEntries(int start, int end)
		throws SystemException {
		return wallEntryPersistence.findAll(start, end);
	}

	public int getWallEntriesCount() throws SystemException {
		return wallEntryPersistence.countAll();
	}

	public WallEntry updateWallEntry(WallEntry wallEntry)
		throws SystemException {
		wallEntry.setNew(false);

		return wallEntryPersistence.update(wallEntry, true);
	}

	public WallEntry updateWallEntry(WallEntry wallEntry, boolean merge)
		throws SystemException {
		wallEntry.setNew(false);

		return wallEntryPersistence.update(wallEntry, merge);
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