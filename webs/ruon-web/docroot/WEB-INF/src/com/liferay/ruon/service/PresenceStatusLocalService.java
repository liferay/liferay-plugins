/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.ruon.service;

/**
 * <a href="PresenceStatusLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface PresenceStatusLocalService {
	public com.liferay.ruon.model.PresenceStatus addPresenceStatus(
		com.liferay.ruon.model.PresenceStatus presenceStatus)
		throws com.liferay.portal.SystemException;

	public void deletePresenceStatus(long presenceStatusId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deletePresenceStatus(
		com.liferay.ruon.model.PresenceStatus presenceStatus)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceStatus getPresenceStatus(
		long presenceStatusId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.ruon.model.PresenceStatus> getPresenceStatuss(
		int start, int end) throws com.liferay.portal.SystemException;

	public int getPresenceStatussCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceStatus updatePresenceStatus(
		com.liferay.ruon.model.PresenceStatus presenceStatus)
		throws com.liferay.portal.SystemException;

	public void populateWithDefaultStatuses()
		throws com.liferay.ruon.util.RUONException;
}