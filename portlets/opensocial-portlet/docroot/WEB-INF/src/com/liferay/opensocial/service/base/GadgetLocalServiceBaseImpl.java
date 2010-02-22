/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalService;
import com.liferay.opensocial.service.persistence.GadgetPersistence;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * <a href="GadgetLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class GadgetLocalServiceBaseImpl implements GadgetLocalService {
	public Gadget addGadget(Gadget gadget) throws SystemException {
		gadget.setNew(true);

		return gadgetPersistence.update(gadget, false);
	}

	public Gadget createGadget(long gadgetId) {
		return gadgetPersistence.create(gadgetId);
	}

	public void deleteGadget(long gadgetId)
		throws PortalException, SystemException {
		gadgetPersistence.remove(gadgetId);
	}

	public void deleteGadget(Gadget gadget) throws SystemException {
		gadgetPersistence.remove(gadget);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return gadgetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return gadgetPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public Gadget getGadget(long gadgetId)
		throws PortalException, SystemException {
		return gadgetPersistence.findByPrimaryKey(gadgetId);
	}

	public List<Gadget> getGadgets(int start, int end)
		throws SystemException {
		return gadgetPersistence.findAll(start, end);
	}

	public int getGadgetsCount() throws SystemException {
		return gadgetPersistence.countAll();
	}

	public Gadget updateGadget(Gadget gadget) throws SystemException {
		gadget.setNew(false);

		return gadgetPersistence.update(gadget, true);
	}

	public Gadget updateGadget(Gadget gadget, boolean merge)
		throws SystemException {
		gadget.setNew(false);

		return gadgetPersistence.update(gadget, merge);
	}

	public GadgetLocalService getGadgetLocalService() {
		return gadgetLocalService;
	}

	public void setGadgetLocalService(GadgetLocalService gadgetLocalService) {
		this.gadgetLocalService = gadgetLocalService;
	}

	public GadgetPersistence getGadgetPersistence() {
		return gadgetPersistence;
	}

	public void setGadgetPersistence(GadgetPersistence gadgetPersistence) {
		this.gadgetPersistence = gadgetPersistence;
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

	@BeanReference(name = "com.liferay.opensocial.service.GadgetLocalService")
	protected GadgetLocalService gadgetLocalService;
	@BeanReference(name = "com.liferay.opensocial.service.persistence.GadgetPersistence")
	protected GadgetPersistence gadgetPersistence;
}