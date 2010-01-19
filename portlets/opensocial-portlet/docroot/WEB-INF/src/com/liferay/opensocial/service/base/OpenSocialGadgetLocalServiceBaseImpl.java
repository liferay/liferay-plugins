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

package com.liferay.opensocial.service.base;

import com.liferay.opensocial.model.OpenSocialGadget;
import com.liferay.opensocial.service.OpenSocialGadgetLocalService;
import com.liferay.opensocial.service.persistence.OpenSocialGadgetPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="OpenSocialGadgetLocalServiceBaseImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class OpenSocialGadgetLocalServiceBaseImpl
	implements OpenSocialGadgetLocalService {
	public OpenSocialGadget addOpenSocialGadget(
		OpenSocialGadget openSocialGadget) throws SystemException {
		openSocialGadget.setNew(true);

		return openSocialGadgetPersistence.update(openSocialGadget, false);
	}

	public OpenSocialGadget createOpenSocialGadget(long openSocialGadgetId) {
		return openSocialGadgetPersistence.create(openSocialGadgetId);
	}

	public void deleteOpenSocialGadget(long openSocialGadgetId)
		throws PortalException, SystemException {
		openSocialGadgetPersistence.remove(openSocialGadgetId);
	}

	public void deleteOpenSocialGadget(OpenSocialGadget openSocialGadget)
		throws SystemException {
		openSocialGadgetPersistence.remove(openSocialGadget);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return openSocialGadgetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return openSocialGadgetPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public OpenSocialGadget getOpenSocialGadget(long openSocialGadgetId)
		throws PortalException, SystemException {
		return openSocialGadgetPersistence.findByPrimaryKey(openSocialGadgetId);
	}

	public List<OpenSocialGadget> getOpenSocialGadgets(int start, int end)
		throws SystemException {
		return openSocialGadgetPersistence.findAll(start, end);
	}

	public int getOpenSocialGadgetsCount() throws SystemException {
		return openSocialGadgetPersistence.countAll();
	}

	public OpenSocialGadget updateOpenSocialGadget(
		OpenSocialGadget openSocialGadget) throws SystemException {
		openSocialGadget.setNew(false);

		return openSocialGadgetPersistence.update(openSocialGadget, true);
	}

	public OpenSocialGadget updateOpenSocialGadget(
		OpenSocialGadget openSocialGadget, boolean merge)
		throws SystemException {
		openSocialGadget.setNew(false);

		return openSocialGadgetPersistence.update(openSocialGadget, merge);
	}

	public OpenSocialGadgetLocalService getOpenSocialGadgetLocalService() {
		return openSocialGadgetLocalService;
	}

	public void setOpenSocialGadgetLocalService(
		OpenSocialGadgetLocalService openSocialGadgetLocalService) {
		this.openSocialGadgetLocalService = openSocialGadgetLocalService;
	}

	public OpenSocialGadgetPersistence getOpenSocialGadgetPersistence() {
		return openSocialGadgetPersistence;
	}

	public void setOpenSocialGadgetPersistence(
		OpenSocialGadgetPersistence openSocialGadgetPersistence) {
		this.openSocialGadgetPersistence = openSocialGadgetPersistence;
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

	@BeanReference(name = "com.liferay.opensocial.service.OpenSocialGadgetLocalService")
	protected OpenSocialGadgetLocalService openSocialGadgetLocalService;
	@BeanReference(name = "com.liferay.opensocial.service.persistence.OpenSocialGadgetPersistence")
	protected OpenSocialGadgetPersistence openSocialGadgetPersistence;
}