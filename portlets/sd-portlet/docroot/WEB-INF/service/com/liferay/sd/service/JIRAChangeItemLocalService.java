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

package com.liferay.sd.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="JIRAChangeItemLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(rollbackFor =  {
	PortalException.class, SystemException.class})
public interface JIRAChangeItemLocalService {
	public com.liferay.sd.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException;

	public com.liferay.sd.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId);

	public void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteJIRAChangeItem(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.sd.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.sd.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getJIRAChangeItemsCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.sd.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException;

	public com.liferay.sd.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem, boolean merge)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.sd.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId) throws com.liferay.portal.SystemException;
}