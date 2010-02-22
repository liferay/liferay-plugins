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

package com.liferay.opensocial.service;

/**
 * <a href="GadgetLocalServiceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetLocalServiceWrapper implements GadgetLocalService {
	public GadgetLocalServiceWrapper(GadgetLocalService gadgetLocalService) {
		_gadgetLocalService = gadgetLocalService;
	}

	public com.liferay.opensocial.model.Gadget addGadget(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.addGadget(gadget);
	}

	public com.liferay.opensocial.model.Gadget createGadget(long gadgetId) {
		return _gadgetLocalService.createGadget(gadgetId);
	}

	public void deleteGadget(long gadgetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetLocalService.deleteGadget(gadgetId);
	}

	public void deleteGadget(com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		_gadgetLocalService.deleteGadget(gadget);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.opensocial.model.Gadget getGadget(long gadgetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.getGadget(gadgetId);
	}

	public java.util.List<com.liferay.opensocial.model.Gadget> getGadgets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.getGadgets(start, end);
	}

	public int getGadgetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.getGadgetsCount();
	}

	public com.liferay.opensocial.model.Gadget updateGadget(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.updateGadget(gadget);
	}

	public com.liferay.opensocial.model.Gadget updateGadget(
		com.liferay.opensocial.model.Gadget gadget, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.updateGadget(gadget, merge);
	}

	public com.liferay.opensocial.model.Gadget addGadget(long companyId,
		java.lang.String name, java.lang.String url, java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.addGadget(companyId, name, url, xml);
	}

	public void destroyGadgets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetLocalService.destroyGadgets();
	}

	public java.util.List<com.liferay.opensocial.model.Gadget> getGadgets(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.getGadgets(companyId, start, end);
	}

	public int getGadgetsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.getGadgetsCount(companyId);
	}

	public void initGadgets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetLocalService.initGadgets();
	}

	public com.liferay.opensocial.model.Gadget updateGadget(long gadgetId,
		java.lang.String name, java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetLocalService.updateGadget(gadgetId, name, xml);
	}

	public GadgetLocalService getWrappedGadgetLocalService() {
		return _gadgetLocalService;
	}

	private GadgetLocalService _gadgetLocalService;
}