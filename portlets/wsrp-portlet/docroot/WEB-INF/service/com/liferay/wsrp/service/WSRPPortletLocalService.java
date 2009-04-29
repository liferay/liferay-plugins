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

package com.liferay.wsrp.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="WSRPPortletLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WSRPPortletLocalService {
	public com.liferay.wsrp.model.WSRPPortlet addWSRPPortlet(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet createWSRPPortlet(long portletId);

	public void deleteWSRPPortlet(long portletId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteWSRPPortlet(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wsrp.model.WSRPPortlet getWSRPPortlet(long portletId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> getWSRPPortlets(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPPortletsCount() throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet updateWSRPPortlet(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet updateWSRPPortlet(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet, boolean merge)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wsrp.model.WSRPPortlet getPortlet(
		java.lang.String portletName)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> getPortlets()
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> getPortlets(
		java.lang.String producerEntityId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> getPortlets(
		java.lang.String producerEntityId, java.lang.String portletHandle)
		throws com.liferay.portal.SystemException;
}