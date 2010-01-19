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

package com.liferay.opensocial.service;

/**
 * <a href="OpenSocialGadgetLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetLocalServiceWrapper
	implements OpenSocialGadgetLocalService {
	public OpenSocialGadgetLocalServiceWrapper(
		OpenSocialGadgetLocalService openSocialGadgetLocalService) {
		_openSocialGadgetLocalService = openSocialGadgetLocalService;
	}

	public com.liferay.opensocial.model.OpenSocialGadget addOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.addOpenSocialGadget(openSocialGadget);
	}

	public com.liferay.opensocial.model.OpenSocialGadget createOpenSocialGadget(
		long openSocialGadgetId) {
		return _openSocialGadgetLocalService.createOpenSocialGadget(openSocialGadgetId);
	}

	public void deleteOpenSocialGadget(long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_openSocialGadgetLocalService.deleteOpenSocialGadget(openSocialGadgetId);
	}

	public void deleteOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		_openSocialGadgetLocalService.deleteOpenSocialGadget(openSocialGadget);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	public com.liferay.opensocial.model.OpenSocialGadget getOpenSocialGadget(
		long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.getOpenSocialGadget(openSocialGadgetId);
	}

	public java.util.List<com.liferay.opensocial.model.OpenSocialGadget> getOpenSocialGadgets(
		int start, int end) throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.getOpenSocialGadgets(start, end);
	}

	public int getOpenSocialGadgetsCount()
		throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.getOpenSocialGadgetsCount();
	}

	public com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.updateOpenSocialGadget(openSocialGadget);
	}

	public com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget,
		boolean merge) throws com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.updateOpenSocialGadget(openSocialGadget,
			merge);
	}

	public com.liferay.opensocial.model.OpenSocialGadget addOpenSocialGadget(
		long companyId, java.lang.String name, java.lang.String url,
		java.lang.String xml)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.addOpenSocialGadget(companyId,
			name, url, xml);
	}

	public void deleteWSRPConsumer(long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_openSocialGadgetLocalService.deleteWSRPConsumer(openSocialGadgetId);
	}

	public java.util.List<com.liferay.opensocial.model.OpenSocialGadget> getOpenSocialGadgets(
		long companyId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.getOpenSocialGadgets(companyId,
			start, end);
	}

	public int getOpenSocialGadgetsCount(long companyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.getOpenSocialGadgetsCount(companyId);
	}

	public com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		long openSocialGadgetId, java.lang.String name, java.lang.String xml)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _openSocialGadgetLocalService.updateOpenSocialGadget(openSocialGadgetId,
			name, xml);
	}

	public OpenSocialGadgetLocalService getWrappedOpenSocialGadgetLocalService() {
		return _openSocialGadgetLocalService;
	}

	private OpenSocialGadgetLocalService _openSocialGadgetLocalService;
}