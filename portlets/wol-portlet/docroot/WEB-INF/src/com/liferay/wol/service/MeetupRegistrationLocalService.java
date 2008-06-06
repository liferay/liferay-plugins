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

package com.liferay.wol.service;

/**
 * <a href="MeetupRegistrationLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface MeetupRegistrationLocalService {
	public com.liferay.wol.model.MeetupRegistration addMeetupRegistration(
		com.liferay.wol.model.MeetupRegistration meetupRegistration)
		throws com.liferay.portal.SystemException;

	public void deleteMeetupRegistration(long meetupRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteMeetupRegistration(
		com.liferay.wol.model.MeetupRegistration meetupRegistration)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupRegistration> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupRegistration> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupRegistration getMeetupRegistration(
		long meetupRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.wol.model.MeetupRegistration updateMeetupRegistration(
		com.liferay.wol.model.MeetupRegistration meetupRegistration)
		throws com.liferay.portal.SystemException;
}